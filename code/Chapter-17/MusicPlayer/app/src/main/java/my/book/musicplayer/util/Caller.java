package my.book.musicplayer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 */
public class Caller {
	
	/**
	 * Cache for most recent request
	 */
	private static RequestCache requestCache = null;

	
	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	public static void setRequestCache(RequestCache requestCache) {
		Caller.requestCache = requestCache;
	}
	
	public static String createStringFromIds(int[] ids){
		if(ids == null)
			return "";
		
		String query ="";
		
		for(int id : ids){
			query = query + id + "+";
		}
		
		return query;
	}

}
