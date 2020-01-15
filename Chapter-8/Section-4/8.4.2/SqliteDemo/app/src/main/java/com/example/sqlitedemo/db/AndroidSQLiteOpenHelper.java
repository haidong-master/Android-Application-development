package com.example.sqlitedemo.db;

import com.example.sqlitedemo.Books;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 可以通过SQLiteOpenHelper的以下两个方法来或得SQLiteDatabase的对象：
 * getReadableDatabase() 创建或者打开一个查询数据库
 * getWritableDatabase() 创建或者打开一个可写数据库 
 */
public class AndroidSQLiteOpenHelper extends SQLiteOpenHelper {

	// 数据库名称
	public static final String DBNAME = "Books.db";
	// 数据库版本
	public static final int VERSION = 2;
	// 建表语句，大小写不敏感
	private static final String CREATETABLE = "create table if not exists "
			+ Books.TABLENAME
			+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name string, author string, reserve INTEGER)";

	/**
	 * 构造函数，必须实现
	 * @param context 上下文路径
	 * @param name 数据库名称
	 * @param factory 可选游标工厂，通常为NULL
	 * @param version 当前数据库版本号
	 */
	public AndroidSQLiteOpenHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	//数据库第一次创建时会调用，一般在其中创建数据库表
	@Override
	public void onCreate(SQLiteDatabase db) {
		//使用execSQL()方法执行DDL语句，如果没有异常，这个方法没有返回值
		db.execSQL(CREATETABLE);
	}

	//当数据库需要修改的时候，Android系统会主动的调用这个方法
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		this.deleteDB(db);
		this.onCreate(db);
	}

	// 删除表
	private void deleteDB(SQLiteDatabase db) {
		db.execSQL("drop table if exists " + Books.TABLENAME);
	}
	
	//打开数据库时的回调函数
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
	
	@Override
	public synchronized void close() {
		super.close();
	}

}
