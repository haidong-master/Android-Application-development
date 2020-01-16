package binderdemo.example.com.binderdemo;


import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class binderActivity extends Activity {

	private static final String LOG_TAG = binderActivity.class.getSimpleName(); 
	
    private IOperation mIOperation;

    public ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(LOG_TAG, "Service connected");
            mIOperation = IOperation.Stub.asInterface(service);
            binder_state.append("connected\n");
        }

        public void onServiceDisconnected(ComponentName name) {
        	binder_state.append("diconnected\n");
            Log.e(LOG_TAG, "disconnected");
            //This is where the current story ends. once disconnected we are not able to automatically reconnect
            mIOperation = null;
        }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    findViews();
	     
        setListensers();
        
        //We can currently not perform a bindService because the native code
        //did not register to the activity manager
        bindService(new Intent("com.example.binderdemo.IOperation"),serviceConnection, BIND_AUTO_CREATE);
    }
	 public void onDestroy(){
        super.onDestroy();
        unbindService(serviceConnection);
    }
	    
    private int      mNum1 = 0;
    private int      mNum2 = 0;
    private Button   button_add;
    private EditText field_num1;
    private EditText field_num2;
    private TextView view_result;
    private TextView view_suggest;
    private TextView binder_state;
    
    private void findViews() {
    	button_add = (Button)findViewById(R.id.submit);
    	field_num1 = (EditText)findViewById(R.id.num1);
    	field_num2 = (EditText)findViewById(R.id.num2);
    	view_result = (TextView) findViewById(R.id.result);
        binder_state = (TextView) findViewById(R.id.binderState);
    }
    
    //Listen for button clicks
    private void setListensers() {
    	button_add.setOnClickListener(calcAdd);
    }

    private OnClickListener calcAdd = new OnClickListener() {
        public void onClick(View v) {
    	  try {
    		  mNum1 = Integer.parseInt(field_num1.getText().toString());
    		  mNum2 = Integer.parseInt(field_num2.getText().toString());
    		  view_result.setText("a + b =" + mIOperation.add(mNum1, mNum2));
          } catch (RemoteException e) {
              Log.w(LOG_TAG," Failed to invoke add:" + e.getMessage(),e);
          }
        }
    };
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
