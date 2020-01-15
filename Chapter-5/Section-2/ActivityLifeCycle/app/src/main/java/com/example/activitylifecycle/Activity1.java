package com.example.activitylifecycle;

import com.example.activitylifecycle.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity1 extends Activity {
	
	private static final String TAG = "Activity1";
	private Context context = this;
	private int param = 1;
	private Button mbtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Activity1 Task id is " + getTaskId());
        
        setContentView(R.layout.layout_1);
        findViews();
		setListensers(); 
    }
    private void findViews(){
    	mbtn = (Button) findViewById(R.id.btn);
    }
    private void setListensers(){
    	
    	mbtn.setOnClickListener(new View.OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				
 			   Intent intent = new Intent();
               intent.setClass(Activity1.this, Activity2.class);
              
               startActivity(intent);
 			}
 		});
    }
   
    /*

    @Override
    protected void onStart() {
    	super.onStart();
    	Log.i(TAG, "onStart called.");
    }

    @Override
    protected void onRestart() {
    	super.onRestart();
    	Log.i(TAG, "Activity1 onRestart called.");
    }
 
    

    @Override
    protected void onResume() {
    	super.onResume();
    	Log.i(TAG, "onResume called.");
    }
    

    @Override
    protected void onPause() {
    	super.onPause();
    	Log.i(TAG, "onPause called.");

    }
    

    @Override
    protected void onStop() {
    	super.onStop();
    	Log.i(TAG, "onStop called.");	
    }
    

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.i(TAG, "onDestory called.");
    }

    /*

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	super.onWindowFocusChanged(hasFocus);
    	Log.i(TAG, "onWindowFocusChanged called.");
    }*/


    /*
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("param", param);
		Log.i(TAG, "onSaveInstanceState called. put param: " + param);
		super.onSaveInstanceState(outState);
	}
	*/

    /*
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		param = savedInstanceState.getInt("param");
		Log.i(TAG, "onRestoreInstanceState called. get param: " + param);
		super.onRestoreInstanceState(savedInstanceState);
	}
	
	@Override  
    public void onConfigurationChanged(Configuration newConfig) {  
        super.onConfigurationChanged(newConfig);  
        Log.i(TAG, "onConfigurationChanged called.");  
        switch (newConfig.orientation) {  
        case Configuration.ORIENTATION_PORTRAIT:  
            setContentView(R.layout.layout_1);  
            break;  
        case Configuration.ORIENTATION_LANDSCAPE:  
            setContentView(R.layout.layout_1);  
            break;  
        }  
    } */ 
	
}