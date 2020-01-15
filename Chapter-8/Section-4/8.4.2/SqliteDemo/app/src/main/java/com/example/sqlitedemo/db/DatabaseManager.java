package com.example.sqlitedemo.db;

import java.util.ArrayList;

import com.example.sqlitedemo.Books;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager {
	private static String TAG = "SqliteDemo"; 
	private AndroidSQLiteOpenHelper dbHelper;
	private SQLiteDatabase db;
	public final static String BOOK_ID = "book_id";
	public DatabaseManager(Context context) {
		dbHelper = new AndroidSQLiteOpenHelper(context);
		db = dbHelper.getWritableDatabase();
	}
	public Cursor select() {		
		Cursor cursor = db
				.query(Books.TABLENAME, null, null, null, null, null, null);
		return cursor;
	}
	// 插入记录
	public int insert(Books book) {
		Log.d(TAG, "----insert----");
		db.beginTransaction();
		try {
			db.execSQL("insert into " + Books.TABLENAME
					+ " values(null, ?, ?, ?)", new Object[] {
					book.name, book.author, book.reserve });
			
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return 0;
		} finally {
			db.endTransaction();
		}
		return 1;
	}
	public void delete(int id) {
		db.beginTransaction();
		
		String where = "_id" + "=?";
		String[] whereValue = { Integer.toString(id) };
		
		db.delete(Books.TABLENAME, where, whereValue);
		
		db.setTransactionSuccessful();
		db.endTransaction();
	}
	
	// 删除记录
	public int delete(Books book) {
		Log.e(TAG, "----delete----");
		
		db.beginTransaction();
		try {
			db.execSQL("delete from " + Books.TABLENAME + " where name = ?",
					new Object[] { book.name });
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return 0;
		} finally {
			db.endTransaction();
		}
		return 1;
	}

	// 更新记录
	public int update(Books book) {
		
		db.beginTransaction();
		try {
			
			
			db.execSQL("update " + Books.TABLENAME + " set " 
					+ " author=?, reserve=? where name=?", new Object[] {
					 book.author, book.reserve, book.name});
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return 0;
		} finally {
			db.endTransaction();
		}
		return 1;
	}

	// 查询记录
	public ArrayList<Books> query(String name) {
		Log.e(TAG, "----query----");
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor;
		Books book;
		ArrayList<Books> list = new ArrayList<Books>();
		// 若fileId为null或""则查询所有记录
		if (name == null || name.equals("")) {
			cursor = db.rawQuery("select * from " + Books.TABLENAME, null);
		} else {
			cursor = db.rawQuery("select * from " + Books.TABLENAME
					+ " where name=?", new String[] { name });
		}
		while (cursor.moveToNext()) {
			book = new Books();
			//book._id = cursor.getInt(cursor.getColumnIndex("_id"));
			book.name = cursor.getString(cursor.getColumnIndex("name"));
			book.author = cursor.getString(cursor.getColumnIndex("author"));
			book.reserve = cursor.getInt(cursor.getColumnIndex("reserve"));
			
			Log.d(TAG, "id= "+ book._id + "BookName ="+ book.name + "author =" + book.author);
			
			list.add(book);
		}
		cursor.close();
		if (list.size() == 0) {
			Log.e("SQLite", "****表中无数据****");
		}
		return list;
	}
	public Cursor queryTheCursor() {  
		//Cursor c = db.query(Books.TABLENAME, null, null, null, null, null, null);
	    Cursor c = db.rawQuery("SELECT * FROM book", null);  
	    return c;  
	}  

	public void closeDB() {  
		db.close();  
	}
}
