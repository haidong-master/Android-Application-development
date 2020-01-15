package com.example.servicelife;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity{

    private Button Startbtn;
    private Button Stopbtn;
    private TextView viewLog;
    
    private Intent  serviceIntent;
    private MyService mService;
    private boolean mBound; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  
		serviceIntent = new Intent(this, MyService.class); 
		
		findViews();
	      
	    setListensers();
	}

	protected void findViews(){
		Startbtn = (Button)findViewById(R.id.startSer);
		Stopbtn  = (Button)findViewById(R.id.stopSer);
		viewLog  = (TextView) findViewById(R.id.tvView);
	}
	
	protected void setListensers(){
		Startbtn.setOnClickListener(startServerClick);
		Stopbtn.setOnClickListener(stopServerClick);
	}
	 private OnClickListener startServerClick = new OnClickListener() {
        public void onClick(View v) {
        	viewLog.setText(R.string.start_Service);
        	startService(serviceIntent);
        }     
	 };
	 private OnClickListener stopServerClick = new OnClickListener() {
        public void onClick(View v) {
        	viewLog.setText(R.string.stop_Service);
        	stopService(serviceIntent);
        }     
	 };
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
}
