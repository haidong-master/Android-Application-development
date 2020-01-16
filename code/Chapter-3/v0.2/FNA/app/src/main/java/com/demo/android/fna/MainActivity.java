package com.demo.android.fna;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	
	private long    m_itemId;
	private Spinner m_spinner;
	private Button  m_btn;
	private ArrayAdapter<CharSequence> m_adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViews();
        setListeners();      
	}
	private void findViews(){
		m_spinner = (Spinner) findViewById(R.id.spinner1);
		m_btn     = (Button) findViewById(R.id.analyseBtn);
		m_adapter = ArrayAdapter.createFromResource(
	                this, R.array.fruits, R.layout.fruits_item);
		m_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}
	private void setListeners(){
		
		m_btn.setOnClickListener(analyseCalc);
		
		m_spinner.setAdapter(m_adapter);
        m_spinner.setOnItemSelectedListener(
	        new OnItemSelectedListener() {
	            public void onItemSelected(
	                    AdapterView<?> parent, View view, int position, long id) {  
	            	m_itemId = id;
	            }
	
	            public void onNothingSelected(AdapterView<?> parent) {
	            	Toast.makeText(MainActivity.this, "Spinner1: unselected", Toast.LENGTH_LONG).show();
	            }
	        });
	}
	private Button.OnClickListener analyseCalc = new OnClickListener()
    {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putLong("FNA_FRUITS", m_itemId);
			intent.setClass(MainActivity.this, Analyse.class);
			intent.putExtras(bundle);    
			startActivity(intent);
		} 	
    };
	
	private void openOptionsDialog(String title, String msg) {
		new AlertDialog.Builder(this).setTitle(title)
			.setMessage(msg)
			.setPositiveButton("ȷ��",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
	
					}
				}).show();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
	    // TODO Auto-generated method stub
       switch(item.getItemId()){
       case R.id.action_about:      
    	   //Toast.makeText(MainActivity.this, "Action about", Toast.LENGTH_SHORT).show();
    	   String content = "author: luhaidong; mail: luhaidong88@gmail.com";
    	   openOptionsDialog("About", content);
           break;
       case R.id.action_settings:          
           Toast.makeText(MainActivity.this, "Action Settings", Toast.LENGTH_SHORT).show();
 //   	   openOptionsDialog(title, content);
           break; 
       }       
	   return super.onOptionsItemSelected(item);
	}
/*	static boolean flag = false;
	public boolean onPrepareOptionsMenu(Menu menu) {

		if(flag){
        	menu.findItem(R.id.action_about).setVisible(false);
        	flag = false;
		}else{
			menu.findItem(R.id.action_about).setVisible(true);
        	flag = true;
		}
        menu.findItem(R.id.action_settings).setVisible(true);      
        return super.onPrepareOptionsMenu(menu);
    }*/
}



