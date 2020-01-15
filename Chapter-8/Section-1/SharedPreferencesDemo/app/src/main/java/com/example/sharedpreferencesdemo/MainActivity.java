package com.example.sharedpreferencesdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText user = null;
	private EditText password = null;
	
	private Button loginBtn = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.pass);
        loginBtn = (Button)findViewById(R.id.loginButton);
        initView();
        loginBtn.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN){
			
					SharedPreferences userInfo = getSharedPreferences("user_info", 0);
					userInfo.edit().putString("name", user.getText().toString()).commit();
					userInfo.edit().putString("pass", password.getText().toString()).commit();
				}
				else if(event.getAction()==MotionEvent.ACTION_UP){
					
				}
				return false;
			}
        	
        });
    }
	private void initView() {
		SharedPreferences userInfo = getSharedPreferences("user_info", 0);
		String username = userInfo.getString("name", "");
		String pass = userInfo.getString("pass", "");
		user.setText(username);
		password.setText(pass);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

}
