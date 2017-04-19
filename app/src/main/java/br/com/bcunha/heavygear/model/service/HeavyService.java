package br.com.bcunha.heavygear.model.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
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

public class HeavyService extends Service {

    private String LOG_TAG = "HeavyService";
    private final int timer_WIFI = 10000; // 10sec
    private final int timer_3G = 1000000;
    private IBinder mBinder = new HeavyBinder();
    private BuscaCotacaoInterface apiClient;
    private Worker worker;
    private List<Acao> watchList;
    private Handler handler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "onBind");
        if (worker != null) {
            handler.postDelayed(worker, timer_WIFI);
        }

        // Buscar watchList  do intent
        watchList = new ArrayList<Acao>();
        watchList.add(new Acao("PETR3.SA", "Petrobras", "", 00.00, true));
        watchList.add(new Acao("JBSS3.SA", "JBS", "", 00.00, true));
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (worker != null) {
            handler.postDelayed(worker, timer_WIFI);
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

        Log.i(LOG_TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class Worker implements Runnable {
        public Context context;
        private static final String ACTION_HEAVYSERVICE = "ACTION_HEAVYSERVICE";

        public Worker(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            apiClient.getQuotes(
            ApiClient.QUERY_QUOTE.replace("?codigo?", formatCodigo(watchList)),
            ApiClient.ENV,
            ApiClient.FORMAT)
            .enqueue(new Callback<RespostaQuote>() {
                @Override
                public void onResponse(Call<RespostaQuote> call,
                                       Response<RespostaQuote> response) {
                    List<Quote> quoteAcoes = response.body().getQuery().getResults().getQuote();
                    List<Acao> acoes = new ArrayList<Acao>();

                    for (Quote quote : quoteAcoes) {
                        acoes.add(new Acao(quote.getsymbol(),
                        quote.getName(),
                        "",
                        Double.parseDouble(quote.getLastTradePriceOnly())));
                    }

                    Intent intent = new Intent(ACTION_HEAVYSERVICE);
                    intent.putParcelableArrayListExtra("watchList", (ArrayList) acoes);

                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    Log.i(LOG_TAG, "Manipulando o resultado");
                }

                @Override
                public void onFailure(Call<RespostaQuote> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_SHORT).show();
                }
            });

            Log.i(LOG_TAG, "Consulta Executada");

            handler.postDelayed(this, timer_WIFI);
        }
    }

    public class HeavyBinder extends Binder {
        public HeavyService getService() {
            return HeavyService.this;
        }
    }

    public String formatCodigo(List<Acao> acoes) {
        if (acoes.size() == 0) {
            return "\"\"";
        }

        StringBuffer codigos = new StringBuffer();
        boolean primeiro = true;
        for (Acao acao : acoes) {
            if (primeiro) {
                codigos.append("\"").append(acao.getCodigo().toString()).append("\"");
                primeiro = false;
            } else {
                codigos.append(",").append("\"").append(acao.getCodigo().toString()).append("\"");
            }
        }
        return codigos.toString();
    }

    public String buscaTextoTeste() {
        return "teste";
    }
}
