package socketdemo.example.com.socketdemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SocketClientJNI extends Activity {

	private static String TAG = "SocketDemo";
	private TextView tv_msg = null;
    private EditText ed_msg = null;
    private Button btn_send = null;

    private static final String HOST = "192.168.1.104";
    private static final int PORT = 9999;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String content = "";
    private String text = null;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        tv_msg = (TextView) findViewById(R.id.TextView);
        ed_msg = (EditText) findViewById(R.id.EditText01);
        btn_send = (Button) findViewById(R.id.Button02);
           
    	if(getSocket() == -1){
    		Log.d(TAG, "getSocket() == -1");
    		ShowDialog("c socket exception");
    	}
        btn_send.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String msg = ed_msg.getText().toString();
                sendBuf(msg);
                mHandler.sendMessage(mHandler.obtainMessage()); 
            	content =HOST +":"+ msg +"\n";
            }
        });
    }

    public void ShowDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("notification").setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
	
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            // TODO Auto-generated method stub
	
	        }
	    }).show();
    }
      
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_msg.setText(tv_msg.getText().toString() + content);
        }
    };
	public native int getSocket();
	public native void sendBuf(String buf);
    static {
        System.loadLibrary("native_socket");
    }
}
