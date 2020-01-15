package com.example.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Activity3 extends Activity {
	private static final String TAG = "Activity1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Activity3 Task id is " + getTaskId());

		setContentView(R.layout.layout_3);
	}
}
