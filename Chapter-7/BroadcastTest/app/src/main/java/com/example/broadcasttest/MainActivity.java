package com.example.broadcasttest;


import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	private static final String TAG = "BroadcastTest";
	
	private IntentFilter mFilter;
	private BroadcastReceiver mReceiver1;
	
	private Button dynamicbtn;	
	private EditText recMsg;
	private Button staticbtn;	
	public static EditText recStaticMsg;
	private static final String INTENAL_ACTION_BROADCAST_DYNAMIC = "com.expamle.music.Broadcast.dynamic";
	private static final String INTENAL_ACTION_BROADCAST_DYNAMIC_BACK = "com.expamle.music.Broadcast.dynamic.back";
	private static final String INTENAL_ACTION_BROADCAST_STATIC = "com.expamle.music.Broadcast.static";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_main);
	    
	   mFilter = new IntentFilter();
	   mFilter.addAction(INTENAL_ACTION_BROADCAST_DYNAMIC);
	
	   mReceiver1 = new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	            handleEvent(context, intent);
	        }
	    };
  
	    recMsg = (EditText) findViewById(R.id.recMsg);
	    dynamicbtn = (Button) findViewById(R.id.dynamicbtn);
	    dynamicbtn.setOnClickListener(dybtnListener);
	    recStaticMsg = (EditText) findViewById(R.id.recStaticMsg);
	    staticbtn = (Button) findViewById(R.id.staticbtn);
	    staticbtn.setOnClickListener(stbtnListener);
	    
	}
	
	private OnClickListener dybtnListener = new OnClickListener() {

	    @Override
	    public void onClick(View v) {
			Log.d(TAG, "dybtnListener OnClickListener");
	        if (v.getId() == (R.id.dynamicbtn)) {
	            Intent intent = new Intent(INTENAL_ACTION_BROADCAST_DYNAMIC);
	            sendBroadcast(intent);
	        }
	    }
	};
	private OnClickListener stbtnListener = new OnClickListener() {
		
	    @Override
	    public void onClick(View v) {

	        if (v.getId() == (R.id.staticbtn)) {
				Log.d(TAG, "stbtnListener OnClickListener");
	            Intent intent = new Intent(INTENAL_ACTION_BROADCAST_STATIC);
	            sendBroadcast(intent);
	        }
	    }
	};
	 private void handleEvent(Context context, Intent intent) {
	        String action = intent.getAction();
	        if (INTENAL_ACTION_BROADCAST_DYNAMIC.equals(action)) {
	        	Log.d(TAG, "Receive service back Broadcast");
	        	StringBuilder text = new StringBuilder(); 
	        	text.append("rec com.expamle.music.Broadcast.dynamic.back"); 
	        	recMsg.setText(text); 
	        }
	 }
	 
	@Override
	public void onResume() {
	    super.onResume();
		Log.d(TAG, "onResume");
	    registerReceiver(mReceiver1, mFilter); //��̬����
	}
	
	@Override
	public void onPause() {
	    super.onPause();	
	   unregisterReceiver(mReceiver1);//��̬����
	}
}
