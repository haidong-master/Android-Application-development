package com.example.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private MyServiceAIDL myServiceAIDL;
    private Intent binderIntent;
    private final static boolean create_flag=true;
    private final static boolean destory_flag=false;
    private final static String TAG="MainActivity";

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myServiceAIDL = MyServiceAIDL.Stub.asInterface(service);
            try {
                //通过AIDL远程调用
                Log.d(TAG,"++start download++");
                myServiceAIDL.DownLoad();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"++MainActivity onCreate++");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开启服务
        Intent intent = new Intent(this, MainService.class);
        startService(intent);

        //连接远程Service和Activity
        binderIntent = new Intent(this,MainService.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("flag",create_flag);
        binderIntent.putExtras(bundle);
        bindService(binderIntent, sc, BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "++MainActivity onDestroy++");

        boolean flag = false;
        //暂停服务
        Intent intent = new Intent(this, MainService.class);
        stopService(intent);

        //断开与远程Service的连接
        unbindService(sc);
    }
}
