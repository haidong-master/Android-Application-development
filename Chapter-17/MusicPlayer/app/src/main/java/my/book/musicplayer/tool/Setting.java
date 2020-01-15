package my.book.musicplayer.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import my.book.musicplayer.R;

public class Setting {
	/**
	 * SD�����ظ���Ŀ¼
	 * */
	public static final String DOWNLOAD_MUSIC_DIRECTORY="/Music/download_music/";
	/**
	 * SD�����ظ��Ŀ¼
	 * */
	public static final String DOWNLOAD_LYRIC_DIRECTORY="/Music/download_lyric/";
	/**
	 * SD������ר��ͼƬĿ¼
	 * */
	public static final String DOWNLOAD_ALBUM_DIRECTORY="/Music/download_album/";
	/**
	 * SD�����ظ���ͼƬĿ¼
	 * */
	public static final String DOWNLOAD_ARTIST_DIRECTORY="/Music/download_artist/";
	/**
	 * ϵͳ���õı�����ļ���
	 * */
	public static final String PREFERENCE_NAME = "org.app.music.settings";
	public static final String KEY_SKINID = "skin_id";
	private SharedPreferences settingPreference;
	public static final String KEY_AUTO_SLEEP="sleep";//��ʱ�ر�ʱ��
	public static final String KEY_BRIGHTNESS="brightness";//��Ļģʽ->1:��ģʽ 0:ҹ��ģʽ
	public static final float KEY_DARKNESS=0.1f;//ҹ��ģʽֵlevel
	/**
	 * Ƥ����Դ��ID����
	 * */
	public static final int[] SKIN_RESOURCES = { R.drawable.main_bg01,
			R.drawable.main_bg02, R.drawable.main_bg03, R.drawable.main_bg04,
			R.drawable.main_bg05, R.drawable.main_bg06 ,R.drawable.bg_media_library};

	public Setting(Context context,boolean isWrite) {
		settingPreference = context.getSharedPreferences(PREFERENCE_NAME,isWrite?Context.MODE_WORLD_READABLE:Context.MODE_WORLD_WRITEABLE);
	}
	/**
	 * ��ȡ���
	 * */
	public String getValue(String key){
		return settingPreference.getString(key, null);
	}
	/**
	 * ��ȡƤ����ԴID
	 * */
	public int getCurrentSkinResId() {
		int skinIndex = settingPreference.getInt(KEY_SKINID, 0);
		if (skinIndex >= SKIN_RESOURCES.length) {
			skinIndex = 0;
		}
		return SKIN_RESOURCES[skinIndex];
	}

	/**
	 * ��ȡƤ��Id
	 * */
	public int getCurrentSkinId(){
		int skinIndex = settingPreference.getInt(KEY_SKINID, 0);
		if (skinIndex >= SKIN_RESOURCES.length) {
			skinIndex = 0;
		}
		return skinIndex;
	}
	
	/**
	 * ����Ƥ����ԴID
	 * */
	public void setCurrentSkinResId(int skinIndex) {
		Editor it = settingPreference.edit();
		it.putInt(KEY_SKINID, skinIndex);
		it.commit();
	}
	
	/**
	 * ���ü�ֵ
	 * */
	public void setValue(String key,String value){
		Editor it = settingPreference.edit();
		it.putString(key, value);
		it.commit();
	}
	
}
