package br.com.bcunha.heavygear.model.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.model.api.ApiClient;
import br.com.bcunha.heavygear.model.api.BuscaCotacaoInterface;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.Quote;
import br.com.bcunha.heavygear.model.pojo.RespostaQuote;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeavyGearService extends Service {

    private static final String LOG_TAG = "HeavyGearService";
    private static final String PREF_FREQUENCIA_ATUALIZACAO = "pref_frequencia_atualizacao";
    private int frequenciaAtualizacao;

    private boolean ativo = false;
    private IBinder mBinder = new HeavyBinder();
    private BuscaCotacaoInterface apiClient;

    public Worker worker;
    public Handler handler = new Handler();
    public List<Acao> watchList;

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "onBind");

        // Buscar watchList  do intent
        watchList = (ArrayList) intent.getParcelableArrayListExtra("watchList");

        if (worker != null) {
            handler.post(worker);
        }
        ativo = true;

        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (worker != null) {
            handler.postDelayed(worker, frequenciaAtualizacao);
        }

        // Buscar watchList  do intent
        watchList = new ArrayList<Acao>();

        Log.i(LOG_TAG, "onStartCommand");
        //return(START_STICKY);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        apiClient = ApiClient.getRetrofit().create(BuscaCotacaoInterface.class);
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
            if(watchList.size() > 1) {
                apiClient.getQuotes(
                ApiClient.QUERY_QUOTE.replace("?codigo?", ApiClient.formatCodigo(watchList)),
                ApiClient.ENV,
                ApiClient.FORMAT)
                .enqueue(new Callback<RespostaQuote>() {
                    @Override
                    public void onResponse(Call<RespostaQuote> call,
                                           Response<RespostaQuote> response) {
                        for (Quote quote : response.body().getQuery().getResults().getQuote()) {
                            int index = watchList.indexOf(new Acao(String.valueOf(quote.getsymbol().toCharArray(),
                            0,
                            quote.getsymbol().length() - 3)));
                            if (index >= 0) {
                                watchList.get(index).setCotacao(quote.getLastTradePriceOnly() != null ? Double.parseDouble(quote.getLastTradePriceOnly()) : 0);
                            }
                        }

                        Intent intent = new Intent(ACTION_HEAVYSERVICE);
                        intent.putParcelableArrayListExtra("watchList", (ArrayList) watchList);

                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    }

                    @Override
                    public void onFailure(Call<RespostaQuote> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Serviço Offline", Toast.LENGTH_LONG).show();
                    }
                });

                Log.i(LOG_TAG, "Consulta Executada");
            }

            handler.postDelayed(this, frequenciaAtualizacao);
        }
    }

    public class HeavyBinder extends Binder {
        public HeavyGearService getService() {
            return HeavyGearService.this;
        }
    }
}
