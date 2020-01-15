package com.example.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity2  extends Activity {
	private static final String TAG = "Activity1";  
	 
	private Button mbtn;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Activity2 Task id is " + getTaskId());
        
        setContentView(R.layout.layout_2);
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
               intent.setClass(Activity2.this, Activity3.class);
              
               startActivity(intent);
 			}
 		});
    }
    @Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "Activity2 onDestory called.");
	}
    /*
	@Override
	protected void onStart() {
		super.onStart();
		//Log.i(TAG, "Second Activity onStart called.");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
	//	Log.i(TAG, "Second Activity onRestart called.");
	}
	@Override
	protected void onResume() {
		super.onResume();
	//	Log.i(TAG, "Second Activity onResume called.");
	}
	  
	@Override
	protected void onPause() {
		super.onPause();
	//	Log.i(TAG, "Second Activity onPause called.");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	//	Log.i(TAG, "Second Activity onStop called.");	
	}
	
	
	    
	    */
}
