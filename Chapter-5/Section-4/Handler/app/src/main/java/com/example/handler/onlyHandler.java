package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class onlyHandler  extends Activity {
	private static String TAG = "onlyHandler";	
	private Handler mHandler = new Handler(){
		@Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Log.d(TAG, "handle id = " + Thread.currentThread().getId());
            switch(msg.what){
            case 1:
            	break;
            case 2:
            	break;
            }
        }
	};	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_handler);
        Log.d(TAG, "Activity id = " + Thread.currentThread().getId());
        mHandler.sendEmptyMessage(1);
    }   
}



