package my.book.musicplayer.util;

import java.util.WeakHashMap;

import android.graphics.Bitmap;

/**
 * Caches downloaded images, saves bandwidth and user's
 * packets
 */
public class ImageCache extends WeakHashMap<String, Bitmap> {

	private static final long serialVersionUID = 1L;
	
	public boolean isCached(String url){
		return containsKey(url) && get(url) != null;
	}
}
