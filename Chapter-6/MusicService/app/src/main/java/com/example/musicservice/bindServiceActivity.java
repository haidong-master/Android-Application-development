package com.example.musicservice;

import com.example.musicservice.service.bindMusicService;
import com.example.musicservice.service.bindMusicService.LocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.musicservice.R;

public class bindServiceActivity extends Activity implements OnClickListener {

	private Button playBtn;
	private Button stopBtn;
	private Button pauseBtn;
	private Button exitBtn;
	
	boolean mBound = false;  
	bindMusicService mService;  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bind_music_service);

		playBtn = (Button) findViewById(R.id.play);
		stopBtn = (Button) findViewById(R.id.stop);
		pauseBtn = (Button) findViewById(R.id.pause);
		exitBtn = (Button) findViewById(R.id.exit);
 
		playBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);
		pauseBtn.setOnClickListener(this);
		exitBtn.setOnClickListener(this);

		connection();
	}

	private void connection() {
		Intent intent = new Intent(this, bindMusicService.class);
		intent.setAction("com.example.musicservice.service.bindMusicReceiver");
		bindService(intent, sc, Context.BIND_AUTO_CREATE);				// bindService
	}

	@Override
	public void onClick(View v) {
		 if(mBound) {
			switch (v.getId()) {
			case R.id.play:
				mService.play();
				break;
			case R.id.stop:
				if (mService != null) {
					mService.stop();
				}
				break;
			case R.id.pause:
				if (mService != null) {
					mService.pause();
				}
				break;
			case R.id.exit:
				this.finish();
				break;
			}
		 }
	}

	private ServiceConnection sc = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {		//connect Service
			mBound = true;
			LocalBinder binder = (LocalBinder) service;                      
            mService = binder.getService();      
            mBound = true;            

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {					//disconnect Service
			mService = null;
			mBound = false;     
		}

	};
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
		if(sc != null){
			unbindService(sc);				// unBindService
			mBound = false; 
		}
	}
}
