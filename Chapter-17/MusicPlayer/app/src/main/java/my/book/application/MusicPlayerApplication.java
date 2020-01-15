package my.book.application;


import my.book.musicplayer.util.Caller;
import my.book.musicplayer.util.ImageCache;
import my.book.musicplayer.util.RequestCache;
import android.app.Application;


public class MusicPlayerApplication extends Application {
	
	public static String TAG = "MusicPlayerApplication";

	//Ӧ��ʵ��
	private static MusicPlayerApplication instance;
	//����ͼ�񻺴�
	private ImageCache mImageCache;
	//web ����
	private RequestCache mRequestCache;
	
	public static MusicPlayerApplication getInstance() {
		return instance;
	}
	@Override
    public void onCreate() {
        super.onCreate();
        mImageCache = new ImageCache();
		mRequestCache = new RequestCache();

		Caller.setRequestCache(mRequestCache);
        instance = this;
    }
}