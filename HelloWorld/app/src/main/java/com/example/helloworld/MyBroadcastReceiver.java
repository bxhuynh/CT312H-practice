package com.example.helloworld;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    String msg = "Android : ";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(msg, "received");
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
    }
}
