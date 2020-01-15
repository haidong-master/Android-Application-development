package com.example.sharepreferencescomplexdata;


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
	 * ��Bitmapת�����ַ�����SharedPreferences
	 * 
	 * ע��:
	 * ��ѹ��ͼƬ�������ʱ,��Ҫѡ��CompressFormat.JPEG�����PNG,��������ͼƬ�к�ɫ����.
	 * ���ο����϶�
	 */
	private void saveBitmapToSharedPreferences(){
		Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.img);

		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 80, byteArrayOutputStream);

		byte[] byteArray=byteArrayOutputStream.toByteArray();
		String imageString=new String(Base64.encodeToString(byteArray, Base64.DEFAULT));

		SharedPreferences sharedPreferences=getSharedPreferences("testSP", Context.MODE_PRIVATE);
		Editor editor=sharedPreferences.edit();
		editor.putString("image", imageString);
		editor.commit();
	}
	
	
	/**
	 * ��SharedPreferences��ȡ��Bitmap
	 */
	private void getBitmapFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("testSP", Context.MODE_PRIVATE);

        String imageString = sharedPreferences.getString("image", "");

        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

        Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
        mImageView.setImageBitmap(bitmap);
    }

}
