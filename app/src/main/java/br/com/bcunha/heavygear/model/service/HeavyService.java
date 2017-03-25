package br.com.bcunha.heavygear.model.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.bcunha.heavygear.model.api.ApiClient;
import br.com.bcunha.heavygear.model.api.ApiInterface;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.RespostaSimplesMultipla;
import br.com.bcunha.heavygear.ui.adapters.RvAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.bcunha.heavygear.R.id.recyclerView;

public class HeavyService extends Service {

    private String LOG_TAG = "HeavyService";
    private final int timer_WIFI = 10000; // 10sec
    private final int timer_3G   = 1000000;
    private HeavyBinder mBinder = new HeavyBinder();
    private ApiInterface apiClient;
    private Worker worker;
    private List<Acao> watchList;
    private Handler handler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "onBind");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (worker != null) {
            handler.postDelayed(worker, timer_WIFI);
        }

        Log.i(LOG_TAG, "onStartCommand");
        //return(START_STICKY);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = ApiClient.getRetrofit().create(ApiInterface.class);

        watchList = new ArrayList<Acao>();
        Acao acaoBRFS3 = new Acao("BRFS3.SA", "Brasil Foodas S.A" , "",  51.11, true);
        Acao acaoITSA4 = new Acao("ITSA4.SA", "Itau SA"           , "",  13.11, true);
        Acao acaoGGBR3 = new Acao("GGBR4.SA", "Gerdau"            , "",  13.11, true);
        Acao acaoGOAU4 = new Acao("GOAU4.SA", "Metalurgica Gerdau", "",  13.11, true);
        Acao acaoJBSS3 = new Acao("JBSS3.SA", "JBS"               , "",  13.11, true);

        watchList.add(acaoBRFS3);
        watchList.add(acaoITSA4);
        watchList.add(acaoGGBR3);
        watchList.add(acaoGOAU4);
        watchList.add(acaoJBSS3);

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
            apiClient.getQueryValorLista(
            ApiClient.QUERY_QUOTE_LISTA.replace("?codigo?", formatCodigo(watchList)),
            ApiClient.ENV,
            ApiClient.FORMAT)
            .enqueue(new Callback<RespostaSimplesMultipla>() {
                @Override
                public void onResponse(Call<RespostaSimplesMultipla> call,
                                       Response<RespostaSimplesMultipla> response) {
                    //                      rvAdapter = new RvAdapter().createFromQuote(response.body().getQuery().getResults().getQuote());
                    //                      recyclerView.setAdapter(rvAdapter);

                    List<RespostaSimplesMultipla.Quote> quoteAcoes = response.body().getQuery().getResults().getQuote();
                    List<Acao> acoes  = new ArrayList<Acao>();

                    for (RespostaSimplesMultipla.Quote quote: quoteAcoes) {
                        acoes.add(new Acao (quote.getsymbol(),
                        quote.getName(),
                        "", //da erro quando vai pegar o preco da acao selecionada na pesquisa
                        Double.parseDouble(quote.getLastTradePriceOnly())));
                    }


                    Intent intent = new Intent(ACTION_HEAVYSERVICE);
                    intent.putParcelableArrayListExtra("watchList", (ArrayList) acoes);

                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    Log.i(LOG_TAG, "Manipulando o resultado:" + response.body().getQuery().getResults().getQuote().get(1).getLastTradePriceOnly());
                }

                @Override
                public void onFailure(Call<RespostaSimplesMultipla> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                }
            });

            Log.i(LOG_TAG, "Consulta Executada");

            handler.postDelayed(this, timer_WIFI);
        }
    }

    public class HeavyBinder extends Binder {
        public HeavyService getService() {
            return(HeavyService.this);
        }
    }

    public String formatCodigo(List<Acao> acoes) {
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

    public String minhaString(){
        return "TESTE DE BINDER";
    }
}
