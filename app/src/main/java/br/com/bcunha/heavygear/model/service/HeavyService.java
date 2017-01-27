package br.com.bcunha.heavygear.model.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.Window;

public class HeavyService extends Service {

    private Worker worker;

    public HeavyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (worker != null && !worker.isAlive()) {
            worker.start();
        }
        return(START_STICKY);
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        worker = new Worker();
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
            while (ativo && count < 100) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                Log.i("Teste", "Contador"+count);
            }
            stopSelf();
        }
    }
}
