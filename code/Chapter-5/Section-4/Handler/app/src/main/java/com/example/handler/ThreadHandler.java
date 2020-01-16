package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class ThreadHandler extends Activity {
	private static String TAG = "ThreadHandler";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_handler);
        Log.d(TAG, "Activity id= " + Thread.currentThread().getId());
        
        Thread thread = new Thread(r);
        thread.start();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	
    Runnable r = new Runnable() {
        @Override
        public void run() {
        	Log.d(TAG, "Runnable id =" + Thread.currentThread().getId());
        	myHandler.sendEmptyMessage(1);  
        }
    };
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
        	Log.d(TAG, "Handler id =" + Thread.currentThread().getId());
        	switch(msg.what){
             case 1:
             	break;
             case 2:
             	break;
             }
        }
    };
}