package com.example.localbroadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private IntentFilter m_intentFilter;
	private LocalReceiver m_localReceiver;
	private LocalBroadcastManager m_localBroadcastManager;
	
	private Button  m_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		m_localBroadcastManager = LocalBroadcastManager.getInstance(this);
		
		m_btn     = (Button) findViewById(R.id.sendBtn);
		m_btn.setOnClickListener(SendCalc);
		m_intentFilter = new IntentFilter();
		m_intentFilter.addAction("com.example.localbroadcast.LOCAL_BROADCAST");
		
		m_localReceiver = new LocalReceiver();
		m_localBroadcastManager.registerReceiver(m_localReceiver,m_intentFilter);//ע�᱾�ؼ�����

	}
	private OnClickListener SendCalc = new OnClickListener()
    {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent("com.example.localbroadcast.LOCAL_BROADCAST");
			m_localBroadcastManager.sendBroadcast(intent);
		} 	
    };
	@Override
    protected void onDestroy(){
		super.onDestroy();
		m_localBroadcastManager.unregisterReceiver(m_localReceiver);
    }
}
