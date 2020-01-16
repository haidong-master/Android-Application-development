package com.example.mycontentprovideroffered;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider{
	private MySQLiteOpenHelper dbHelper;

	// 定义一个UriMatcher类
	private static final UriMatcher MATCHER = new UriMatcher(
			UriMatcher.NO_MATCH);
	private static final int BOOK_TABLE = 1;
	private static final int BOOKS_TABLE = 2;
	static {
		MATCHER.addURI("com.example.mycontentprovideroffered", "books_table", BOOK_TABLE);
		MATCHER.addURI("com.example.mycontentprovideroffered", "books_table/#", BOOKS_TABLE);
	}

	// 删除数据
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count = 0;
		switch (MATCHER.match(uri)) {
			case BOOK_TABLE:
				count = db.delete("books_table", selection, selectionArgs);
				return count;

			case BOOKS_TABLE:
				long id = ContentUris.parseId(uri);
				String where = "_id=" + id;
				if (selection != null && !"".equals(selection)) {
					where = selection + " and " + where;
				}
				count = db.delete("books_table", where, selectionArgs);
				return count;

			default:
				throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	// 返回当前操作的数据的mimeType
	@Override
	public String getType(Uri uri) {
		switch (MATCHER.match(uri)) {
			case BOOK_TABLE:
				return "vnd.android.cursor.dir/books_table";
			case BOOKS_TABLE:
				return "vnd.android.cursor.item/books_table";
			default:
				throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	// 插入数据
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Uri insertUri = null;
		switch (MATCHER.match(uri)) {
			case BOOK_TABLE:
				long rowid = db.insert("books_table", "", values);
				insertUri = ContentUris.withAppendedId(uri, rowid);
				break;

			default:
				throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
		return insertUri;
	}

	//在ContentProvider创建的时候执行
	@Override
	public boolean onCreate() {
		Log.d("contentprovideroffered", "ContentProvider - onCreate()");
		dbHelper = new MySQLiteOpenHelper(this.getContext(),"BOOKS.db",null,1);
		return false;
	}

	// 查询数据
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
						String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
			case BOOK_TABLE:
				// 查询所有的数据
				return db.query("books_table", projection, selection, selectionArgs,
						null, null, sortOrder);

			case BOOKS_TABLE:
				// 查询某个ID的数据
				// 通过ContentUris这个工具类解释出ID
				long id = ContentUris.parseId(uri);
				String where = " _id=" + id;
				if (!"".equals(selection) && selection != null) {
					where = selection + " and " + where;

				}
				return db.query("books_table", projection, where, selectionArgs, null,
						null, sortOrder);

			default:
				throw new IllegalArgumentException("unknow uri" + uri.toString());
		}

	}

	// 更新数据
	@Override
	public int update(Uri uri, ContentValues values, String selection,
					  String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count = 0;
		switch (MATCHER.match(uri)) {
			case BOOK_TABLE:
				count = db.update("books_table", values, selection, selectionArgs);
				break;
			case BOOKS_TABLE:
				// 通过ContentUri工具类得到ID
				long id = ContentUris.parseId(uri);
				String where = "_id=" + id;
				if (selection != null && !"".equals(selection)) {
					where = selection + " and " + where;
				}
				count = db.update("books_table", values, where, selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
		return count;
	}
}
