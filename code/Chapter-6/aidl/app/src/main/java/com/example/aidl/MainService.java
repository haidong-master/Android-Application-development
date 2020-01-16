package com.example.aidl;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by hxlu on 4/6/2016.
 */
public class MainService extends Service {

    boolean flag;
    private final static String TAG = "MainService";

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "++MainService onDestroy++");
        flag = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "++MainService onCreate++");

        Notification no = new Notification(R.mipmap.ic_launcher, "有通知到来", System.currentTimeMillis());
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
      //  no.setLatestEventInfo(this, "AIDLDemo", "running", pi);
        startForeground(1, no);
    }

    @Override
    public IBinder onBind(Intent intent) {

        Bundle bundle = intent.getExtras();
        flag = bundle.getBoolean("flag");
        System.out.println(flag);
        return ms;
    }

    MyServiceAIDL.Stub ms = new MyServiceAIDL.Stub() {
        @Override
        public void DownLoad() throws RemoteException {

            new Thread(new Runnable() {
                int i = 0;

                @Override
                public void run() {
                    //未达到线程条件，会一直在后台运行，就算服务已经关闭
                    while (flag) {

                        try {
                            i++;
                            System.out.println("i的值是" + i);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("退出服务");
                }
            }).start();

        }
    };
}