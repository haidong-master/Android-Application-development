package com.example.contentproviderdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private StringBuilder sb = new StringBuilder();
    private Button   btn;
    private EditText etv;
    private EditText edittv;
    private ListView listview;
    private ArrayAdapter<String> adapter;
    private List<String> contactsList = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayRecords();
        etv = (EditText)findViewById(R.id.etv1);
        edittv = (EditText)findViewById(R.id.etv2);
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new BtnClick());
        listview = (ListView)findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactsList);

        listview.setAdapter(adapter);
        displayRecords();
    }
    private class BtnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //增加数据
            if(v.getId() == R.id.btn)
            {
                String name = etv.getText().toString();
                String phoneNo =edittv.getText().toString();
                insertRecords(name, phoneNo);
                displayRecords();
            }
        }
    }
    private void displayRecords() {
        // 获取联系人信息的Uri
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        // 获取ContentResolver
        ContentResolver contentResolver = this.getBaseContext().getContentResolver();
        // 查询数据，返回Cursor
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {

            // 获取联系人的ID
            String contactId = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            // 获取联系人的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            // 构造联系人信息
            sb.append("contactId=").append(contactId).append(",Name= ")
                    .append(name +"\n");
            // 查询电话类型的数据操作
            Cursor phones = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                            + contactId, null, null);
            while (phones.moveToNext()) {
                String phoneNumber = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                // 添加Phone的信息
                sb.append(",Phone=").append(phoneNumber+"\n");
            }

            phones.close();
            // 查询Email类型的数据操作
            Cursor emails = contentResolver.query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = "
                            + contactId, null, null);
            while (emails.moveToNext()) {
                String emailAddress = emails
                        .getString(emails
                                .getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                // 添加Email的信息
                sb.append(",Email=").append(emailAddress+"\n");
            }
            emails.close();
            contactsList.add(sb.toString());
        }
        cursor.close();
    }
    private void insertRecords(String name, String phoneNo) {
        ContentValues values = new ContentValues();
        // 首先向RawContacts.CONTENT_URI执行一个空值插入，目的是获取系统返回的rawContactId
        Uri rawContactUri = this.getBaseContext().getContentResolver()
                .insert(RawContacts.CONTENT_URI, values);
        // 获取id
        long rawContactId = ContentUris.parseId(rawContactUri);
        // 往data表入姓名数据
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId); // 添加id
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);// 添加内容类型（MIMETYPE）
        values.put(StructuredName.GIVEN_NAME, name);// 添加名字，添加到first name位置
        this.getBaseContext()
                .getContentResolver()
                .insert(android.provider.ContactsContract.Data.CONTENT_URI,
                        values);
        // 往data表入电话数据
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        values.put(Phone.NUMBER, phoneNo);
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        this.getBaseContext()
                .getContentResolver()
                .insert(android.provider.ContactsContract.Data.CONTENT_URI,
                        values);
        // 往data表入Email数据
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
        values.put(Email.DATA, "luhaidong88@gmail.com");
        values.put(Email.TYPE, Email.TYPE_WORK);
        this.getBaseContext()
                .getContentResolver()
                .insert(android.provider.ContactsContract.Data.CONTENT_URI,
                        values);
    }
}
