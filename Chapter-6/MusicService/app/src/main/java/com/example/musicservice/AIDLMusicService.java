package com.example.musicservice;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.musicservice.R;


public class AIDLMusicService extends Service {

	private MediaPlayer mediaPlayer;
	private LinkedList<Music> musicList = new LinkedList<Music>();
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	public final IMusicAIDLService.Stub binder = new IMusicAIDLService.Stub() {

		@Override
		public void play() throws RemoteException {
			if (mediaPlayer == null) {
				mediaPlayer = MediaPlayer.create(AIDLMusicService.this, R.raw.music);
				mediaPlayer.setLooping(false);
			}
			if (!mediaPlayer.isPlaying()) {
				mediaPlayer.start();
			}
		}

		@Override
		public void pause() throws RemoteException {
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				mediaPlayer.pause();
			}
		}

		@Override
		public void stop() throws RemoteException {
			if (mediaPlayer != null) {
				mediaPlayer.stop();
				try {
					mediaPlayer.prepare();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	   @Override
	   public void saveMusicInfo(Music music) throws RemoteException {
               if (music != null){
                       musicList.add(music);
               }
       }

       @Override
       public List<Music> getAllMusic() throws RemoteException {
               return musicList;
       }
	};
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		if(mediaPlayer != null){
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}
}
