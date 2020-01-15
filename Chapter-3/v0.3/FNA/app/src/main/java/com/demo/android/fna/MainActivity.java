package com.demo.android.fna;


import android.net.Uri;
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
        restorePrefers();
        setListeners();      
	}
	private void findViews(){
		m_spinner = (Spinner) findViewById(R.id.spinner1);
		m_btn     = (Button) findViewById(R.id.analyseBtn);
		m_adapter = ArrayAdapter.createFromResource(
	                this, R.array.fruits, R.layout.fruits_item);
		m_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}
	private void restorePrefers(){
		
	}
	private void setListeners(){
		
		m_btn.setOnClickListener(analyseCalc);
		
		m_spinner.setAdapter(m_adapter);
        m_spinner.setOnItemSelectedListener(
	        new OnItemSelectedListener() {
	            public void onItemSelected(
	                    AdapterView<?> parent, View view, int position, long id) {  
	            	CharSequence msg ="Spinner1: position=" + position + " id=" + id;
	            	Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
	            }
	
	            public void onNothingSelected(AdapterView<?> parent) {
	            	Toast.makeText(MainActivity.this, "Spinner1: unselected", Toast.LENGTH_LONG).show();
	            }
	        });
	}
	private OnClickListener analyseCalc = new OnClickListener()
    {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(MainActivity.this, AnalyseActivity.class);

			Bundle bundle = new Bundle();
			bundle.putLong("FNA_FRUITS", m_itemId);
			intent.putExtras(bundle);    

			startActivityForResult(intent, 2);
		} 	
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch (requestCode) {
    	case 2:
    		if (resultCode == RESULT_OK) {
    			 Bundle bunde = data.getExtras();
    	         String returnData = bunde.getString("RETURN_DATA"); 	
    			 Toast.makeText(MainActivity.this, returnData, Toast.LENGTH_LONG).show();
    		}
    		break;
    	default:
    	}
    }

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

	protected void disposeAnalysis(int position){
		String content = null;
		String title = null;
		switch(position){
			case 0:
				title = "苹果成分 （g/100g）";
				content = "水分（%）" + "84.6;"
						+"蛋白质 " +"0.3;"
						+"脂肪" + "0.6;"
						+"总糖" + "1.4;"
						+"粗纤维" + "0.12;"
						+"热量（千卡）"+"58;";

				break;
			case 1:
				title = "香蕉成分 （g/100g）";
				content = "水分（%）" + "76;"
						+"蛋白质 " +"1.2;"
						+"脂肪" + "0.5;"
						+"总糖" + "1.8;"
						+"粗纤维" + "0.9;"
						+"热量（千卡）"+"67;";
				break;
			case 2:
				title = "菠萝成分 （g/100g）";
				content = "水分（%）" + "89;"
						+"蛋白质 " +"0.5;"
						+"脂肪" + "0.4;"
						+"总糖" + "15;"
						+"粗纤维" + "0.4;"
						+"热量（千卡）"+"60;";
				break;
			case 3:
				title = "西瓜成分 （g/100g）";
				content = "水分（%）" + "92;"
						+"蛋白质 " +"0.6;"
						+"脂肪" + "0.4;"
						+"总糖" + "17;"
						+"粗纤维" + "0.9;"
						+"热量（千卡）"+"54;";
				break;
		}
		//openOptionsDialog(title, content);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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



