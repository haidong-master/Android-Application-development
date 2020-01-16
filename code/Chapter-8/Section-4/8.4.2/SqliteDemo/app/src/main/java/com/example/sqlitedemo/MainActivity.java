package com.example.sqlitedemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitedemo.db.DatabaseManager;

public class MainActivity extends Activity implements
		AdapterView.OnItemClickListener {
	private static String TAG = "SqliteDemo"; 
	private EditText BookName;
	private EditText BookAuthor;
	private EditText BookReserve;
	private Button insertBtn;
	private Button queryBtn;
	private Button updateBtn;
	private Button deleteBtn;
	private ListView listView; 
	
	private DatabaseManager dbManager;
	private Cursor mCursor;
	private Books book;
	private int BOOK_ID = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getDataManager();
		
		findViews();
	}
	@Override  
	protected void onDestroy() {  
		super.onDestroy();  
		//Activity关闭时应释放DB  
		dbManager.closeDB();  
	} 

	private void getDataManager()
	{
		dbManager = new DatabaseManager(MainActivity.this);
		
		book = new Books();
		mCursor = dbManager.select();
	}
	private void findViews()
	{
		BookName = (EditText)findViewById(R.id.bookname);
		BookAuthor = (EditText)findViewById(R.id.author);
		BookReserve = (EditText)findViewById(R.id.reserve);
		
		insertBtn = (Button) findViewById(R.id.insert);
		updateBtn = (Button) findViewById(R.id.update);
		deleteBtn = (Button) findViewById(R.id.delete);

		listView = (ListView) findViewById(R.id.listView); 
		listView.setAdapter(new BooksListAdapter(this, mCursor));
		listView.setOnItemClickListener(this);
	}
	
	public void add(View view) {
		book.name = BookName.getText().toString();
		book.author = BookAuthor.getText().toString();
		String reserve_data = BookReserve.getText().toString();

		if (book.name.equals("") || book.author.equals("")||reserve_data.equals("")) {
			return;
		}
		book.reserve = Integer.parseInt(reserve_data);

		dbManager.insert(book);
		//dbManager.query("");
		mCursor.requery();
		listView.invalidateViews();
		BookName.setText("");
		BookAuthor.setText("");
		BookReserve.setText("");
	}

	public void delete(View view) {
		if (BOOK_ID == 0) {
			return;
		}
		Log.d(TAG, "BOOK_ID = "+BOOK_ID);
		dbManager.delete(BOOK_ID);
		//dbManager.query("");
		mCursor.requery();
		listView.invalidateViews();
		BookName.setText("");
		BookAuthor.setText("");
		BookReserve.setText("");
	}

	public void update(View view) {
		book.name = BookName.getText().toString().trim();
		book.author = BookAuthor.getText().toString().trim();
		String reserve_data = BookReserve.getText().toString().trim();

		Log.d(TAG, "update++++");
		if (book.name.equals("") || book.author.equals("")||reserve_data.equals("")) {
			return;
		}
		Log.d(TAG, "update----");
		book.reserve = Integer.parseInt(reserve_data);
		
		dbManager.update(book);
		// 查询记录
		//dbManager.query("");
		mCursor.requery();
		listView.invalidateViews();
		BookName.setText("");
		BookAuthor.setText("");
		BookReserve.setText("");
	}
	public void  query(View view){
		book.name = BookName.getText().toString();
		
		if (book.name.equals("")) {
			return;
		}
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		mCursor.moveToPosition(position);
		BOOK_ID = mCursor.getInt(0);
		BookName.setText(mCursor.getString(1));
		BookAuthor.setText(mCursor.getString(2));

	}
	 public class BooksListAdapter extends BaseAdapter {
		private Context mContext;
		private Cursor mCursor;

		public BooksListAdapter(Context context, Cursor cursor) {

			mContext = context;
			mCursor = cursor;
		}

		@Override
		public int getCount() {
			return mCursor.getCount();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView mTextView = new TextView(mContext);
			mCursor.moveToPosition(position);
			mTextView.setText(mCursor.getString(0)+"," + mCursor.getString(1) + "," + mCursor.getString(2));
			
			return mTextView;
		}
	 }
}