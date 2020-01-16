package my.book.musicplayer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper {
	/**�α�***/
	private Cursor c = null;
	/**����������**/
	private static final String CREATE_TAB = "create table "+ "music(_id integer primary key autoincrement,music_id integer,clicks integer," +"latest text)";
	/**����***/
	private static final String TAB_NAME = "music";
	/**��ݿ�***/
	private SQLiteDatabase db = null;
	/***���캯��**/
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
    /***����һ����ݿ⣬���û�оʹ���һ����ݿ�***/
	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		db.execSQL(CREATE_TAB);
	}
	/**�������**/
	public void insert(ContentValues values){
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TAB_NAME, null, values);
		db.close();
	}
     /*** �������*/
	public void update(ContentValues values,int id){
		SQLiteDatabase db = getWritableDatabase();
		db.update(TAB_NAME, values, "music_id="+id, null);
		db.close();
	}
	/**ɾ�����*/
	public void delete(int id){
		if (db == null){
			db = getWritableDatabase();
		}
		db.delete(TAB_NAME, "music_id=?", new String[]{String.valueOf(id)});
	}
	/***�������*/
	public Cursor query(int id){
		SQLiteDatabase db = getReadableDatabase();
		c = db.query(TAB_NAME, null, "music_id=?", new String[]{String.valueOf(id)}, null, null, null);
		db.close();
		return c;
	}
	/***���������ѯ**/
	public Cursor queryByClicks(){
		SQLiteDatabase db = getReadableDatabase();
		c = db.query(TAB_NAME, null, null, null, null, null, "clicks desc");
		return c;
	}
	/***��ʱ�併���ѯ**/
	public Cursor queryRecently(){
		SQLiteDatabase db = getReadableDatabase();
		c = db.query(TAB_NAME, null, null, null, null, null, "latest desc");
		return c;
	}
	/***�ر���ݿ�***/
	public void close(){
		if (db != null){
			db.close();
			db=null;
		}
		if (c!=null){
			c.close();
			c=null;
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
               
	}

}
