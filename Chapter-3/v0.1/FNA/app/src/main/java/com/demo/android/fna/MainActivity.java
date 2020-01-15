package com.demo.android.fna;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button  m_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		m_btn     = (Button) findViewById(R.id.analyseBtn);
		m_btn.setOnClickListener(analyseCalc);
	}
	private OnClickListener analyseCalc = new OnClickListener()
    {
		@Override
		public void onClick(View arg0) {
			//Toast.makeText(MainActivity.this, "Hello FNA APP",
 			//		Toast.LENGTH_SHORT).show();
			openOptionsDialog();
		} 	
    };
    public void openOptionsDialog(){
    	new AlertDialog.Builder(this).setTitle("About FNA Dialog")
		.setMessage("FNA App")
		.show();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	 }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case R.id.action_about:      
     	     String content = "author: luhaidong; mail: luhaidong88@gmail.com";
 Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
           break;
        case R.id.action_settings:          
           Toast.makeText(MainActivity.this, "Action Settings", Toast.LENGTH_SHORT).show();
           break; 
        }       
 	   return super.onOptionsItemSelected(item);
 	}
    static boolean flag = false;
    public boolean onPrepareOptionsMenu(Menu menu) {
		//super.onPrepareOptionsMenu(menu);
		menu.clear();
		if(flag){
			getMenuInflater().inflate(R.menu.menu_main, menu);
		}else{
			menu.add(0, 1, 1, "seat");
		}
		flag = !flag;
        return super.onPrepareOptionsMenu(menu);
    }

}
