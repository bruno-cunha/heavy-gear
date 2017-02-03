package br.com.bcunha.heavygear.model.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import java.util.List;

import br.com.bcunha.heavygear.model.api.ApiClient;
import br.com.bcunha.heavygear.model.api.ApiInterface;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.RespostaSimplesMultipla;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeavyService extends Service {

    private String LOG_TAG = "HeavyService";
    private HeavyBinder mBinder = new HeavyBinder();
    private ApiInterface apiClient;
    private Worker worker;
    public List<Acao> watchlist;

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "onBind");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (worker != null && !worker.isAlive()) {
            worker.start();
        }
        Log.i(LOG_TAG, "onStartCommand");
        //return(START_STICKY);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = ApiClient.getRetrofit().create(ApiInterface.class);
        worker = new Worker();
        Log.i(LOG_TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class Worker extends Thread {
        public int count = 0;
        public boolean ativo = true;

        public Worker() {
        }

        @Override
        public void run() {
//            while (ativo && count < 100) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                count++;
//                Log.i(LOG_TAG, "Contador"+count);
//            }
//            stopSelf();

            apiClient.getQueryValorLista(
            ApiClient.QUERY_QUOTE_LISTA.replace("?codigo?", formatCodigo(watchlist)),
            ApiClient.ENV,
            ApiClient.FORMAT)
            .enqueue(new Callback<RespostaSimplesMultipla>() {
                @Override
                public void onResponse(Call<RespostaSimplesMultipla> call,
                                       Response<RespostaSimplesMultipla> response) {
//                    rvAdapter = new RvAdapter().createFromQuote(response.body().getQuery().getResults().getQuote());
//                    recyclerView.setAdapter(rvAdapter);
               }
                @Override
                public void onFailure(Call<RespostaSimplesMultipla> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                }
            });
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
