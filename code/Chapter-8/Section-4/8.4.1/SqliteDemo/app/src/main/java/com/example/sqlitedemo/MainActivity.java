package com.example.sqlitedemo;

import com.example.sqlitedemo.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    public class Books {
        public String id;
        public String name;
        public String author;
        public int reserve;
        public Books() {
        }
        public Books(String name, String author, int reserve) {
            this.id = "book_" + String.valueOf(System.currentTimeMillis());
            this.name = name;
            this.author = author;
            this.reserve = reserve;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //打开或创建BOOKS.db数据库
        SQLiteDatabase db = openOrCreateDatabase("BOOKS.db", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS book");
        //创建book表
        db.execSQL("CREATE TABLE book (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, author VARCHAR, reserve SMALLINT)");
        Books book = new Books();
        book.name = "android development";
        book.author = "liqiu";
        book.reserve = 10;
        //插入数据
        db.execSQL("INSERT INTO book VALUES (NULL, ?,?, ?)", new Object[]{book.name, book.author,book.reserve});
        book.name = "mysql explain";
        book.author = "lisi";
        book.reserve = 15;
        //ContentValues以键值对的形式存放数据
        ContentValues cv = new ContentValues();
        cv.put("name", book.name);
        cv.put("author", book.author);
        cv.put("reserve", book.reserve);
        //插入ContentValues中的数据
        db.insert("book", null, cv);
        cv = new ContentValues();
        cv.put("reserve", 15);
        //更新数据
        db.update("book", cv, "name = ?", new String[]{"mysql explain"});
        //查询数据
        Cursor c = db.rawQuery("SELECT * FROM book WHERE reserve >= ?", new String[]{"11"});
        while (c.moveToNext()) {
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            String author = c.getString(c.getColumnIndex("author"));
            int reserve = c.getInt(c.getColumnIndex("reserve"));
            Log.i("db", "_id=>" + _id + ", name=>" + name + ",author=>" + author + ", reserve=>" + reserve);
        }
        c.close();
        //删除数据
        db.delete("book", "reserve < ?", new String[]{"15"});
        //关闭当前数据库
        db.close();
        //删除BOOKS.db数据库
        //deleteDatabase("BOOKS.db");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
