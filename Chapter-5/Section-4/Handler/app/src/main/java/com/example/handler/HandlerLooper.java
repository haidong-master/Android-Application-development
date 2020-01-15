package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class HandlerLooper extends Activity {
	private static String TAG = "HandlerLooper";
	
    private LooperHandler mHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_handler);
        Log.d(TAG, "Activity id = " + Thread.currentThread().getId());
        
        HandlerThread thread = new HandlerThread("MyThread");
        thread.start();
        mHandler = new LooperHandler(thread.getLooper());
        Message msg = mHandler.obtainMessage();
        msg.sendToTarget();
    }

    class LooperHandler extends Handler {
        public LooperHandler() {
            
        }
        
        public LooperHandler(Looper looper) {
            super(looper);
        }
        
        @Override
        public void handleMessage(Message msg) {
        	Log.d(TAG, "LooperHandler id = " + Thread.currentThread().getId());
        }
    }
}