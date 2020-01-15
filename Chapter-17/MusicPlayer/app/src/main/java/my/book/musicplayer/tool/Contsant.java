package my.book.musicplayer.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import my.book.musicplayer.R;
import my.book.musicplayer.tool.XfDialog.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class Contsant {
	/***�ж������Ƿ���**/
	public static boolean getNetIsAvailable(Context context){
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo=connManager.getActiveNetworkInfo();
		if(networkInfo==null){
			return false;
		}
		return networkInfo.isAvailable();
	}
	/**
	 * ��ʾ��Ϣ
	 * */
	public static Toast showMessage(Toast toastMsg, Context context, String msg) {
		if (toastMsg == null) {
			toastMsg = Toast.makeText(context, msg, Toast.LENGTH_LONG);
		} else {
			toastMsg.setText(msg);
		}
		toastMsg.show();
		return toastMsg;
	}
	/**
	 * ɾ���ļ���ɾ��ý��������
	 * */
	public static boolean deleteFile(Context context,String filePath){
		new File(filePath).delete();
		ContentResolver cr=context.getContentResolver();
		int id=-1;
		Cursor cursor=cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{BaseColumns._ID}
		, MediaColumns.DATA+"=?", new String[]{filePath}, null);
		if(cursor.moveToNext()){
			id=cursor.getInt(0);
		}
		cursor.close();
		if(id!=-1){
			return cr.delete(ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id), null, null)>0;
		}
		return false;
	}
	
	
    /**
	 * ������ť�Ի���
	 * */
	public static XfDialog createConfirmDialog(Context context, String text,
			String title, String msg, DialogInterface.OnClickListener listener) {
		Builder builder = new XfDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(text, listener);
		return builder.create();
	}
	/**
	 * ��ȡ��Ļ�Ĵ�С0����� 1���߶�
	 * */
	public static int[] getScreen(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		return new int[] { (int) (outMetrics.density * outMetrics.widthPixels),
				(int) (outMetrics.density * outMetrics.heightPixels) };
	}
	/**
	 * ��ȡ�ļ��ĺ�׺���ش�д
	 * */
	public static String getSuffix(String str) {
		int i = str.lastIndexOf('.');
		if (i != -1) {
			return str.substring(i + 1).toUpperCase();
		}
		return str;
	}
	/**
	 * ��ʽ������->00:00
	 * */
	public static String formatSecondTime(int millisecond) {
		if (millisecond == 0) {
			return "00:00";
		}
		millisecond = millisecond / 1000;
		int m = millisecond / 60 % 60;
		int s = millisecond % 60;
		return (m > 9 ? m : "0" + m) + ":" + (s > 9 ? s : "0" + s);
	}

	/**
	 * ��ʽ���ļ���С Byte->MB
	 * */
	public static String formatByteToMB(int size){
		float mb=size/1024f/1024f;
		return String.format("%.2f",mb);
	}
	/**
	 * ����ļ�·����ȡ�ļ�Ŀ¼
	 * */
	public static String clearFileName(String str) {
		int i = str.lastIndexOf(File.separator);
		if (i != -1) {
			return str.substring(0, i + 1);
		}
		return str;
	}
	/**
	 * ����ļ����ȡ�����׺����ļ���
	 * */
	public static String clearSuffix(String str) {
		int i = str.lastIndexOf(".");
		if (i != -1) {
			return str.substring(0, i);
		}
		return str;
	}
	/**
	 * ����ļ�·����ȡ�����׺����ļ���
	 * */
	public static String clearDirectory(String str) {
		int i = str.lastIndexOf(File.separator);
		if (i != -1) {
			return clearSuffix(str.substring(i + 1, str.length()));
		}
		return str;
	}
	/**
	 * ���SD���Ƿ���װ��
	 * */
	public static boolean isExistSdCard(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	/**
	 * ���SDĿ¼·��
	 * */
	public static String getSdCardPath(){
		return Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * �ж��ļ��Ƿ����
	 * */
	public static boolean isExistFile(String file){
		return new File(file).exists();
	}
	/**
	 * �ж�Ŀ¼�Ƿ���ڣ������򴴽�
	 * */
	public static void isExistDirectory(String directoryName) {
		File file = new File(directoryName);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	/**
	 * �޸��ļ���
	 * */
	public static String renameFileName(String str){
		int i=str.lastIndexOf('.');
		if(i!=-1){
			File file=new File(str);
			file.renameTo(new File(str.substring(0,i)));
			return str.substring(0,i);
		}
		return str;
	}
	/**
	 * ��ȡ�����б����
	 * */
	public static List<HashMap<String, Object>> getListMusicData() {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("icon", String.valueOf(R.drawable.local_singer));
		map2.put("title", "����");
		map2.put("icon2", String.valueOf(R.drawable.playlist_sign));
		data.add(map2);

		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("icon", String.valueOf(R.drawable.local_album));
		map3.put("title", "ר��");
		map3.put("icon2", String.valueOf(R.drawable.playlist_sign));
		data.add(map3);

		HashMap<String, Object> map4 = new HashMap<String, Object>();
		map4.put("icon", String.valueOf(R.drawable.local_file));
		map4.put("title", "���ص�����");
		map4.put("icon2", String.valueOf(R.drawable.playlist_sign));
		data.add(map4);


		HashMap<String, Object> map7 = new HashMap<String, Object>();
		map7.put("icon", String.valueOf(R.drawable.lately_player));
		map7.put("title", "����");
		map7.put("icon2", String.valueOf(R.drawable.playlist_sign));
		data.add(map7);
		return data;
	}
	/***
	 * ��ȡ�б��������
	 * @return
	 */
	public static List<HashMap<String, Object>> getListDownLoadData() {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("icon", String.valueOf(R.drawable.download_icon_down));
		map.put("title", "��������");
		map.put("icon2", String.valueOf(R.drawable.playlist_sign));
		data.add(map);

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("icon", String.valueOf(R.drawable.download_icon_finish));
		map2.put("title", "�������");
		map2.put("icon2", String.valueOf(R.drawable.playlist_sign));
		data.add(map2);
		return data;
	}

}
