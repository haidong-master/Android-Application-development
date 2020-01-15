package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastRec extends BroadcastReceiver {  
	private static final String TAG = "BroadcastTest";
	private static final String INTENAL_ACTION_BROADCAST_STATIC = "com.expamle.music.Broadcast.static";
	private static final String INTENAL_ACTION_BROADCAST_STATIC_BACK = "com.expamle.music.Broadcast.static.back"; 

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "BroadcastRec onReceive");
		 if(intent != null){
			if(INTENAL_ACTION_BROADCAST_STATIC_BACK.equals(intent.getAction())){
		    	Log.d(TAG, "Receive service static Broadcast");
		    	StringBuilder text = new StringBuilder(); 
		    	text.append("rec com.expamle.music.Broadcast.static.back"); 
		    	MainActivity.recStaticMsg.setText(text); 
			} 
		 }  
	}  

}  


