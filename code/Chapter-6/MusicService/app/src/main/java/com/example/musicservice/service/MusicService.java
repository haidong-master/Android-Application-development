package com.example.musicservice.service;


import java.io.IOException;

import com.example.musicservice.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class MusicService extends Service {
	private static final String TAG = "MusicService";
	
	private MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.v(TAG, "onCreate");
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(this, R.raw.music);
			mediaPlayer.setLooping(false);
		}
	}

	@Override
	public void onDestroy() {
		Log.v(TAG, "onDestroy");
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}
	//onStart is replaced by onStartCommand function
	@Override
	public int  onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG, "onStartCommand");
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				int op = bundle.getInt("op");
				switch (op) {
				case 1:
					play();
					break;
				case 2:
					stop();
					break;
				case 3:
					pause();
					break;
				}
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}

	public void play() {
		if (!mediaPlayer.isPlaying()) {
			mediaPlayer.start();
		}
	}

	public void pause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			try {
				mediaPlayer.prepare();	
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
