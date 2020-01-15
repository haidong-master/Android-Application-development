package com.example.servicelife;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	
	private static final String TAG = "MyService";

	@Override
	public IBinder onBind(Intent arg0) {
		Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();  
		return null;
	}

	@Override
	public void onCreate() {
		Log.v(TAG, "onCreate");
		super.onCreate(); 
	}

	@Override
	public void onDestroy() {
		Log.v(TAG, "onDestroy");
		super.onDestroy();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		Log.v(TAG, "onStart");
		super.onStart(intent, startId);
	}
}