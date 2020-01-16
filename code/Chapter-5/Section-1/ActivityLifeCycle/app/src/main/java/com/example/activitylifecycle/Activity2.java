package com.example.activitylifecycle;

import com.example.activitylifecycle.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Activity2  extends Activity {
	private static final String TAG = "Activity2";  
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Log.i(TAG, "Second Activity onCreate called.");
		setContentView(R.layout.layout_2);
	}
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	//	Log.i(TAG, "Second Activity onDestory called.");
	}
	    
}
