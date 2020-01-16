package com.example.musicservice;

import java.util.List;

import com.example.musicservice.R;

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
import android.widget.EditText;
import android.widget.Toast;


public class AIDLMusicActivity extends Activity implements OnClickListener {

	private Button playBtn;
	private Button stopBtn;
	private Button pauseBtn;
	private Button exitBtn;
	private Button addMusicBtn;
	private Button listMusicBtn;
	
	private EditText inputMusicEdit;
	
	private Music music;
	private IMusicAIDLService musicService;
	int index;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aidl_music_activity);

		playBtn = (Button) findViewById(R.id.play);
		stopBtn = (Button) findViewById(R.id.stop);
		pauseBtn = (Button) findViewById(R.id.pause);
		exitBtn = (Button) findViewById(R.id.exit);
		addMusicBtn = (Button) findViewById(R.id.addMusic);
		listMusicBtn = (Button) findViewById(R.id.listMusic);		
		inputMusicEdit = (EditText) findViewById(R.id.edit_text);
		
		playBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);
		pauseBtn.setOnClickListener(this);
		exitBtn.setOnClickListener(this);
		addMusicBtn.setOnClickListener(this);
		listMusicBtn.setOnClickListener(this);
		
		index = 0;
		connection();
	}

	private void connection() {
		Intent intent = new Intent(this, AIDLMusicService.class);
		intent.setAction("com.example.musicservice.AIDLMusic");

		bindService(intent, sc, Context.BIND_AUTO_CREATE);				
	}

	@Override
	public void onClick(View v) {

		try {
			switch (v.getId()) {
			case R.id.play:
				musicService.play();
				break;
			case R.id.stop:
				if (musicService != null) {
					musicService.stop();
				}
				break;
			case R.id.pause:
				if (musicService != null) {
					musicService.pause();
				}
				break;
			case R.id.addMusic:
			{
				 Music music = new Music(); 
                 index = index + 1; 
                 music.settMusicName("test.mp3"); 
                 music.setPlayTime(60*3); 
                 music.setMusicAuthor("luhd"); 
                 try { 
                	 musicService.saveMusicInfo(music); 
                 } catch (RemoteException e) { 
                         e.printStackTrace(); 
                 } 
				break;
			}
			case R.id.listMusic:
			{
				List<Music> list = null; 

                try { 
                        list = musicService.getAllMusic(); 
                } catch (RemoteException e) { 
                        e.printStackTrace(); 
                } 

                if (list != null){ 
                        StringBuilder text = new StringBuilder(); 

                        for(Music music : list){ 
                                text.append("\nMusic name:"); 
                                text.append(music.getMusicName()); 
                                text.append("\n Music play time :"); 
                                text.append(music.getPlayTime()); 
                                text.append("\n Music Author:"); 
                                text.append(music.getMusicAuthor()); 
                        } 

                        inputMusicEdit.setText(text); 
                }else { 
                        Toast.makeText(AIDLMusicActivity.this, "get data error", 
                                        Toast.LENGTH_SHORT).show(); 
                } 
				break;
			}
			case R.id.exit:
				this.finish();
				break;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private ServiceConnection sc = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {		//connect Service
			musicService = IMusicAIDLService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {					//disconnect Service
			musicService = null;
		}

	};
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
		if(sc != null){
			unbindService(sc);				// unBindService
		}
	}
}