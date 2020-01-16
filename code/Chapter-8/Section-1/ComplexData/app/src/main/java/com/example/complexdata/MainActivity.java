package com.example.complexdata;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class MainActivity extends Activity {
    private Button mSaveButton;
    private Button mGetButton;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListensers();
    }

    private void findViews(){
        mSaveButton=(Button) findViewById(R.id.saveButton);

        mGetButton=(Button) findViewById(R.id.getButton);

        mImageView=(ImageView) findViewById(R.id.imageView);
    }
    private void  setListensers(){
        mGetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getBitmapFromSharedPreferences();
            }
        });
        mSaveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBitmapToSharedPreferences();
            }
        });
    }

    /**
     * 将Bitmap转换成字符串保存至SharedPreferences
     *
     * 注意:
     * 在压缩图片至输出流时,不要选择CompressFormat.JPEG而该是PNG,否则会造成图片有黑色背景.
     * 详见参考资料二
     */
    private void saveBitmapToSharedPreferences(){
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.img);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray=byteArrayOutputStream.toByteArray();
        String imageString=new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences=getSharedPreferences("testSP", Context.MODE_PRIVATE);
        Editor editor=sharedPreferences.edit();
        editor.putString("image", imageString);
        editor.commit();
    }


    /**
     * 从SharedPreferences中取出Bitmap
     */
    private void getBitmapFromSharedPreferences(){
        SharedPreferences sharedPreferences=getSharedPreferences("testSP", Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString=sharedPreferences.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray=Base64.decode(imageString, Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
        //第三步:利用ByteArrayInputStream生成Bitmap
        Bitmap bitmap=BitmapFactory.decodeStream(byteArrayInputStream);
        mImageView.setImageBitmap(bitmap);
    }

}
