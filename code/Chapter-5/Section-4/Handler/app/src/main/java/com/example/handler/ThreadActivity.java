package com.example.handler;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ThreadActivity  extends Activity{
    /** Called when the activity is first created. */
	private static String TAG = "ThreadActivity";
	private final int MSG_HELLO = 0;
    private TextView mTextView;
    
	private Handler mHandler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_activity);
        mTextView = (TextView) findViewById(R.id.tv); 
        new CustomThread().start();
        
        findViewById(R.id.send_btn).setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				String str = "Handler";
		        Log.d(TAG, "ThreadActivity id = " + Thread.currentThread().getId());
				mHandler.obtainMessage(MSG_HELLO, str).sendToTarget();
				
			}
		});    
    }
    class CustomThread extends Thread {
    	@Override
    	public void run() {
    		Looper.prepare();
    		mHandler = new Handler(){
    			public void handleMessage (Message msg) {
    				switch(msg.what) {
    				case MSG_HELLO:
    					mTextView.setText("Test ThreadDemo");
    					Log.d(TAG, "CustomThread id = " + Thread.currentThread().getId());
    					break;
    				}
    			}
    		};
    		Looper.loop();
    	}
    }
}




