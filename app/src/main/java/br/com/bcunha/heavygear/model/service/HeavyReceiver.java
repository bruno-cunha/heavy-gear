package br.com.bcunha.heavygear.model.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HeavyReceiver extends BroadcastReceiver {
    public HeavyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        intent = new Intent("HEAVYSERVICE");
        intent.setPackage(".model.service.HeavyService");
        context.startService(intent);
    }
}
