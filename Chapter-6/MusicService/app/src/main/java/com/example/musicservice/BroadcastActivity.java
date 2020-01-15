package com.example.musicservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.musicservice.R;
import com.example.musicservice.broadcastrec.BroadcastRec;
import com.example.musicservice.broadcastrec.BroadcastService;

public class BroadcastActivity extends Activity  implements OnClickListener{

	private Button startBroadService;
	private Button startBroadcast;
	private Intent intent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broad_service_activity);
		startBroadService = (Button) findViewById(R.id.startBroadService);

		startBroadService.setOnClickListener(this);
		startBroadcast = (Button) findViewById(R.id.startBroadcast);

		startBroadcast.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == startBroadService) {
			intent = new Intent(this, BroadcastService.class);
			intent.setAction("com.example.broadcastrec.BroadcastService");
			startService(intent);// startService
		}else{
			intent = new Intent(this, BroadcastRec.class);
			intent.setAction("com.expamle.music.Broadcast.static");
			sendBroadcast(intent);
		}
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();

		if(intent != null){
			stopService(intent);
		}
	}
}
