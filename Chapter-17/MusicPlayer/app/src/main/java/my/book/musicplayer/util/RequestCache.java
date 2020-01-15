package my.book.musicplayer.util;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 */
public class RequestCache {
	
	// TODO cache lifeTime
	
	private static int CACHE_LIMIT = 10;
	
	private LinkedList history;
	private Hashtable<String, String> cache;
	
	public RequestCache(){
		history = new LinkedList();
		cache = new Hashtable<String, String>();
	}
	
	@SuppressWarnings("unchecked")
	public void put(String url, String data){
		history.add(url);
		// too much in the cache, we need to clear something
		if(history.size() > CACHE_LIMIT){
			String old_url = (String) history.poll();
			cache.remove(old_url);
		}
		cache.put(url, data);
	}
	
	public String get(String url){
		return cache.get(url);
	}
}
