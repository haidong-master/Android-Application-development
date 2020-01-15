package com.example.bitmapdemo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{

    private surfaceView bitmapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bitmapView = new surfaceView(this);
        setContentView(bitmapView);
    }
}

