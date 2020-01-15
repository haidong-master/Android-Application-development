package com.demo.hellojni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "HelloJni";
    public  int a, b;
    public  String    mContent;
    public  String    mFilename;
    private TextView  mTvString;
    private TextView mTvInt;
    private Button mButtonOk;
    private EditText mConEdit;
    private EditText  mFileEdit;
    private byte[]  data;
    private int[]   num;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /* Create a TextView and set its content.
         * the text is retrieved by calling a native
         * function.
         */
        setContentView(R.layout.activity_main);


        findViews();
        JNILogicDispose();
        setListeners();
    }

    private void findViews(){
        mTvString = (TextView) findViewById(R.id.tvStr);
        mTvInt = (TextView) findViewById(R.id.tvInt);
        mFileEdit = (EditText) findViewById(R.id.fileName);
        mConEdit = (EditText) findViewById(R.id.fileCont);
        mButtonOk = (Button) findViewById(R.id.btn_ok);
    }

    private void JNILogicDispose(){
        num =  new int [10];
        data = new byte [10];

        for(int i = 0; i < 10; i++){
            num[i] = i;
            data[i] = '0';
        }

        sendPtrToJNI(10, 10, data, num);
        for(int i = 0; i < 10; i++){
            Log.d(TAG, "data =" + (data[i]-48));
            Log.d(TAG, "num =" + num[i]);
        }
        mTvString.setText(getStringFromJNI() );
        mTvInt.setText( "1 + 2 = " + getIntFromJNI(1, 2) );
    }

    private void setListeners()
    {
        mButtonOk.setOnClickListener(okJNI);
    }

    private Button.OnClickListener okJNI = new View.OnClickListener()
    {
        public void onClick(View arg0) {
            mFilename = mFileEdit.getText().toString();
            mContent = mConEdit.getText().toString();
            sendStringToJNI(mContent, mFilename);
        }
    };
    public native String  getStringFromJNI();
    public native int     getIntFromJNI(int a, int b);
    public native void  sendStringToJNI(String xml, String filename);
    public native void  sendPtrToJNI(int i, int j, byte[] data ,int[] count);

    static {
        System.loadLibrary("HelloJni");
    }
}
