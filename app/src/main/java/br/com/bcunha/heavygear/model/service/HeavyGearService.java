package br.com.bcunha.heavygear.model.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.bcunha.heavygear.model.api.alphavantage.ApiAlphaVantage;
import br.com.bcunha.heavygear.model.api.alphavantage.ApiAlphaVantageKey;
import br.com.bcunha.heavygear.model.api.alphavantage.BuscaAtivoInterface;
import br.com.bcunha.heavygear.model.pojo.Ativo;
import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaAcao;
import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaMoeda;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeavyGearService extends Service {

    private static final String LOG_TAG = "HeavyGearService";
    private static final String PREF_FREQUENCIA_ATUALIZACAO = "pref_frequencia_atualizacao";
    private int frequenciaAtualizacao;

    private boolean ativo = false;
    private IBinder mBinder = new HeavyBinder();
    private BuscaAtivoInterface apiAlphaVantageClient;

    public Worker worker;
    public Handler handler = new Handler();
    public List<Ativo> watchListService;

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "onBind");

        // Buscar watchListService  do intent
        watchListService = (ArrayList) intent.getParcelableArrayListExtra("watchListService");

        executar();
        ativo = true;

        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (worker != null) {
            handler.postDelayed(worker, frequenciaAtualizacao);
        }

        // Buscar watchListService  do intent
        watchListService = new ArrayList<Ativo>();

        Log.i(LOG_TAG, "onStartCommand");
        //return(START_STICKY);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        apiAlphaVantageClient = ApiAlphaVantage.getRetrofit().create(BuscaAtivoInterface.class);
        worker = new Worker(this);
        atualizaTimer();

        Log.i(LOG_TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        Log.i(LOG_TAG, "onDestroy");
        stopSelf();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(LOG_TAG, "onUnbind");
        ativo = false;
        return false;
    }

    public void atualizaTimer() {
        frequenciaAtualizacao = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()).getString(PREF_FREQUENCIA_ATUALIZACAO, "10000"));
    }

    private class Worker implements Runnable {
        public Context context;
        private static final String ACTION_HEAVYSERVICE = "ACTION_HEAVYSERVICE";

        public Worker(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            if (!ativo) {
                return;
            }
            if(watchListService.size() >= 1) {
                for (final Ativo ativo : watchListService) {
                    final int index = watchListService.indexOf(ativo);

                    if (watchListService.get(index).getTipo().equals("MOEDA")) {
                        apiAlphaVantageClient.getCurrency(ApiAlphaVantage.DIGITAL_CURRENCY_INTRADAY,
                        ativo.getCodigo(),
                        ApiAlphaVantage.MARKET,
                        ApiAlphaVantageKey.ApiKey)
                        .enqueue(new Callback<RespostaMoeda>() {
                            @Override
                            public void onResponse(Call<RespostaMoeda> call, Response<RespostaMoeda> response) {
                                if (response.body() == null) {
                                    handler.post(worker);
                                    return;
                                }

                                if (index <= watchListService.size()-1) {
                                    Double cotacao = parseDouble(response.body().getTimeSeries().getTimeMomentMoeda().get1aPriceBRL());
                                    Double cotacaoDolar = parseDouble(response.body().getTimeSeries().getTimeMomentMoeda().get1bPriceUSD());
                                    watchListService.get(index).setCotacao(cotacao);
                                    watchListService.get(index).setCotaocaoDolar(cotacaoDolar);
                                    Intent intent = new Intent(ACTION_HEAVYSERVICE);
                                    intent.putExtra("ativo", watchListService.get(index));
                                    intent.putExtra("index", index);
                                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<RespostaMoeda> call, Throwable t) {
                                //Toast.makeText(getApplicationContext(), "Serviço Sem Resposta", Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        apiAlphaVantageClient.getStock(ApiAlphaVantage.TIME_SERIES_DAILY,
                        ativo.getCodigo() + ".SA",
                        ApiAlphaVantage.INTERVAL,
                        ApiAlphaVantageKey.ApiKey)
                        .enqueue(new Callback<RespostaAcao>() {
                            @Override
                            public void onResponse(Call<RespostaAcao> call, Response<RespostaAcao> response) {
                                if (response.body() == null) {
                                    handler.post(worker);
                                    return;
                                }

                                if (index <= watchListService.size()-1) {
                                    Double cotacao = parseDouble(response.body().getTimeSeries().getTimeMomentAcao().get4Close());
                                    watchListService.get(index).setVariacao(calculaVariacao(parseDouble(response.body().getTimeSeries().getTimeMomentAcao().get1Open()), cotacao));
                                    watchListService.get(index).setCotacao(cotacao);
                                    watchListService.get(index).setMinimaDia(parseDouble(response.body().getTimeSeries().getTimeMomentAcao().get3Low()));
                                    watchListService.get(index).setMaximaDia(parseDouble(response.body().getTimeSeries().getTimeMomentAcao().get2High()));
                                    watchListService.get(index).setAbertura(parseDouble(response.body().getTimeSeries().getTimeMomentAcao().get1Open()));
                                    watchListService.get(index).setVolumeNegociacao(parseInteger(response.body().getTimeSeries().getTimeMomentAcao().get5Volume()));

                                    Intent intent = new Intent(ACTION_HEAVYSERVICE);
                                    intent.putExtra("ativo", watchListService.get(index));
                                    intent.putExtra("index", index);
                                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<RespostaAcao> call, Throwable t) {
                                //Toast.makeText(getApplicationContext(), "Serviço Sem Resposta", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }

           //Log.i(LOG_TAG, "Consulta Executada");

            handler.postDelayed(this, frequenciaAtualizacao);
        }
    }

    public void executar(){
        if (worker != null) {
            handler.post(worker);
        }
    }

    public void removeItem(int index) {
        watchListService.remove(index);
        executar();
    }

    public void atualizaWatchList(List<Ativo> acoes){
        this.watchListService = acoes;
        executar();
    }

    public Double parseDouble(String valor){
        return valor != null ? Double.parseDouble(valor) : 0.00;
    }

    public Integer parseInteger(String valor){
        return valor != null ? Integer.parseInt(valor) : 0;
    }

    public Double calculaVariacao(Double cotacaoAntiga, Double novaCotacao){
        if (cotacaoAntiga == 0) {
            return 0.00;
        }
        return novaCotacao - cotacaoAntiga;
    }

    public class HeavyBinder extends Binder {
        public HeavyGearService getService() {
            return HeavyGearService.this;
        }
    }
}
