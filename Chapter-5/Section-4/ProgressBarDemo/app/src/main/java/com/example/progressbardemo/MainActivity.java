package com.example.progressbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button       mButton;  
	private ProgressBar  mProgressBar;  
	private TextView     mTextView;  
	  
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.activity_main);  
	      
	    mButton = (Button)findViewById(R.id.btn);  
	    mProgressBar = (ProgressBar)findViewById(R.id.progressBar);  
	    mTextView = (TextView)findViewById(R.id.tv);  
	      
	    mButton.setOnClickListener(new OnClickListener() {  
	          
	        @Override  
	        public void onClick(View v) {  
	        	MyAsyncTask asyncTask = new MyAsyncTask(mTextView, mProgressBar);  
	            asyncTask.execute(1000);  
	        }  
	    });  
	}  
}
