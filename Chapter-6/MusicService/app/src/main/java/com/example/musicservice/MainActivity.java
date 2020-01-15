package com.example.musicservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.musicservice.R;
public class MainActivity extends Activity  implements OnClickListener {

	private Button musicServiceBtn;
	private Button localServiceBtn;
	private Button AIDLServiceBtn;
	private Button BroadServiceBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		musicServiceBtn = (Button) findViewById(R.id.localServiceActivity);
		localServiceBtn = (Button) findViewById(R.id.bindServiceActivity);
		AIDLServiceBtn = (Button) findViewById(R.id.AIDLMusicActivity);
		BroadServiceBtn = (Button) findViewById(R.id.BroadcastActivity);
		musicServiceBtn.setOnClickListener(this);
		localServiceBtn.setOnClickListener(this);
		AIDLServiceBtn.setOnClickListener(this);
		BroadServiceBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.localServiceActivity:
			startActivity(new Intent(this, StartServiceActivity.class));
			break;
		case R.id.bindServiceActivity:
			startActivity(new Intent(this, bindServiceActivity.class));
			break;
		case R.id.AIDLMusicActivity:
			startActivity(new Intent(this, AIDLMusicActivity.class));
			break;
		case R.id.BroadcastActivity:
			startActivity(new Intent(this, BroadcastActivity.class));
			break;
		}
	}

}
