package com.example.musicservice.broadcastrec;


import java.io.IOException;

import com.example.musicservice.R;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


public class BroadcastService extends Service {
	private static final String TAG = "BroadcastService";
	
	private IntentFilter mFilter;
	private BroadcastReceiver mReceiver;

	private static final String INTENAL_ACTION_BROADCAST_DYNAMIC = "com.expamle.music.Broadcast.dynamic";
	private static final String INTENAL_ACTION_BROADCAST_DYNAMIC_BACK = "com.expamle.music.Broadcast.dynamic.back";

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.v(TAG, "onCreate");
        mFilter = new IntentFilter();
		mFilter.addAction(INTENAL_ACTION_BROADCAST_DYNAMIC);
		
		mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.v(TAG, "BroadcastReceiver");
                handleEvent(context, intent);
            }
        };
        registerReceiver(mReceiver,mFilter);
	}

	@Override
	public void onDestroy() {
		Log.v(TAG, "onDestroy");
		unregisterReceiver(mReceiver);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.v(TAG, "onStart");
	}

	public void dynamicSendBroadcast() {
		Intent intent = new Intent(this,BroadcastRec.class);
		intent.setAction(INTENAL_ACTION_BROADCAST_DYNAMIC_BACK);
		sendBroadcast(intent);
	}
	private void handleEvent(Context context, Intent intent) {
        Log.v(TAG, "BroadcastReceiver handleEvent");
        String action = intent.getAction();
        if (INTENAL_ACTION_BROADCAST_DYNAMIC.equals(action)) {
        	Log.v(TAG, "Broadcast Receive");
        	dynamicSendBroadcast();
        }
    }
}
