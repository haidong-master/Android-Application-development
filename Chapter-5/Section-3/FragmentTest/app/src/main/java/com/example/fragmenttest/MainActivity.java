package com.example.fragmenttest;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button mbtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mbtn = (Button) findViewById(R.id.btn);
		setListensers();
	}
	
	private void setListensers(){
    	mbtn.setOnClickListener(new OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				SecondAboveFragment fragment = new SecondAboveFragment();
 				FragmentManager fm = getFragmentManager();
 				FragmentTransaction ft = getFragmentManager().beginTransaction();
 				ft.replace(R.id.above_fragment, fragment);
 				ft.addToBackStack(null);

 				ft.commit();	
 			}
 		});
    }
}
