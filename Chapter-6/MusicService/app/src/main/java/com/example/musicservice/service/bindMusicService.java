package com.example.musicservice.service;

import java.io.IOException;
import java.util.Random;

import com.example.musicservice.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class bindMusicService extends Service {  
    
	private MediaPlayer mediaPlayer;
	// Binder given to clients  
    private final IBinder mBinder = new LocalBinder();  

    /***  
     * Class used for the client Binder.Because we know this service always  
     * runs in the same process as its clients  
     */       
    public class LocalBinder extends Binder {    
    	public bindMusicService getService() {    
            return bindMusicService.this;    
        }    
    }  

    @Override    
    // 如果是通过startService()函数启动的时候，这个函数是不起作用的。   
    // public void onServiceConnected(ComponentName name, IBinder service) 返回的Activity    
    // IBinder service 对象    
    public IBinder onBind(Intent intent) {    
    	return mBinder;    
    }  

	public void play() {
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(bindMusicService.this, R.raw.music);
			mediaPlayer.setLooping(false);
		}
		if (!mediaPlayer.isPlaying()) {
			mediaPlayer.start();
		}
	}

	public void pause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}			
	}


	public void stop(){
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
