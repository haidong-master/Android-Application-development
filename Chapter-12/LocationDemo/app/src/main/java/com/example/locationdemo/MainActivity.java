package com.example.locationdemo;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private TextView positionText;
    private LocationManager locMgr;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        positionText = (TextView)findViewById(R.id.position_text);
        getMyLocation();
    }

    private void getMyLocation() {
        // 1、获取LocationManager，LocationManager是系统级别的服务
        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 2、获取有效的provider，并显示出来
        List<String> providerlist = locMgr.getAllProviders();
        showProvider(providerlist);
        if(providerlist.contains(LocationManager.GPS_PROVIDER)){
            provider =  LocationManager.GPS_PROVIDER;
        }else if(providerlist.contains(LocationManager.NETWORK_PROVIDER)){
            provider =  LocationManager.NETWORK_PROVIDER;
        }else {
            showInfo("No location provider to use");
        }
        // 3、在LocationManger获取某个provider的位置信息
		/*
		 * 获取位置更新使用getLastKnownLocation()，参数为provider的名字，系统提供预定义的有LocationManager
		 * .GPS_PROVIDER、LocationManager.NETWORK_PROVIDER以及PASSIVE_PROVIDER。
		 * 如果未能获取任何信息
		 * ，getLastKnownLocation(provider)将返回null。Location可以获取provider的名字
		 * ，经纬度，高度，
		 * 速度，方位（bearing），精确度等等，用getXXX()获取，对于非常规数据在获取之前可以通过hasXXX()来判断有否该信息
		 */

        Location locate = locMgr.getLastKnownLocation(provider);
        if(locate != null){
            //显示当前设备的位置信息
            String currentPosition = "latitude is " + locate.getLatitude() + "\n" +"longitude is " + locate.getLongitude();
            showInfo(currentPosition);
            positionText.setText(currentPosition);
        }
        locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10,locationListener);
    }

    private void showProvider(List<String> list) {
        showInfo("show providers : ");
        for (int i = 0; i < list.size(); i++) {
            showInfo("(" + i + ")" + list.get(i));
        }
    }

    private void showInfo(String info) {
        Log.i("MainActivity", info);
    }
    protected void onDestroy(){
        super.onDestroy();
        if(locMgr != null){
            locMgr.removeUpdates(locationListener);
        }
    }
    LocationListener locationListener = new LocationListener(){
        @Override
        public void onLocationChanged(Location arg0) {
            // TODO Auto-generated method stub
            String currentPosition = "latitude is " + arg0.getLatitude() + "\n" +"longitude is " + arg0.getLongitude();
            positionText.setText(currentPosition);
        }
        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onStatusChanged(String arg0, int arg1,
                                    Bundle arg2) {
            // TODO Auto-generated method stub
        }
    };
}

