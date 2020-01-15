package com.example.mycontentprovideruse;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new BtnClick());
        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new BtnClick());
        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new BtnClick());
        btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new BtnClick());
    }

    private class BtnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //增加数据
            if(v.getId() == R.id.btn1)
            {
                ContentResolver cr = MainActivity.this.getContentResolver();

                Uri uri = Uri.parse("content://com.example.mycontentprovideroffered/books_table");
                ContentValues values = new ContentValues();
                values.put("book_name", "图书检测");
                values.put("book_author", "李煜");
                Uri result = cr.insert(uri, values);

                if (ContentUris.parseId(result)>0) {
                    Toast.makeText(MainActivity.this, "增加成功", Toast.LENGTH_SHORT).show();
                }
            }
            //查询数据
            else if(v.getId() == R.id.btn2)
            {
                ContentResolver cr = MainActivity.this.getContentResolver();
                Uri uri = Uri.parse("content://com.example.mycontentprovideroffered/books_table");

                Cursor c = cr.query(uri,null, null, null, null);

                //循环显示
                for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
                    Toast.makeText(MainActivity.this,
                            "第"+c.getInt(0)+"条记录,书名是"+c.getString(1)+",作者是"+c.getString(2),
                            Toast.LENGTH_SHORT).show();
                }

                c.close();
            }
            //删除数据
            else if(v.getId() == R.id.btn3)
            {
                ContentResolver cr = MainActivity.this.getContentResolver();
                //这里删除的是表中第一行数据
                Uri uri = Uri.parse("content://com.example.mycontentprovideroffered/books_table/1");

                int result =cr.delete(uri, null, null);

                if (result >= 1) {
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "这行数据不存在了!", Toast.LENGTH_LONG).show();
                }
            }
            //更新数据
            else if(v.getId() == R.id.btn4)
            {
                ContentResolver cr = MainActivity.this.getContentResolver();
                //这里更改的是表中第二行数据
                Uri uri = Uri.parse("content://com.example.mycontentprovideroffered/books_table/2");

                ContentValues values = new ContentValues();
                values.put("book_name", "程序设计");
                values.put("book_author","清明");
                int result = cr.update(uri, values, null, null);
                if (result >= 1) {
                    Toast.makeText(MainActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "这行数据不存在了!", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
