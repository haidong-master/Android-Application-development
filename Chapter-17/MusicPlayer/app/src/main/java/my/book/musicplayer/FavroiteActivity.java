package my.book.musicplayer;

import my.book.musicplayer.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class FavroiteActivity extends Fragment {
	WebView webview;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		  View parentView =  inflater.inflate(R.layout.favorite, container, false);
		
		  webview=(WebView) parentView.findViewById(R.id.music_webview);
		  webview.loadUrl("http://music.qq.com/midportal/static/recom_v2/");
		  webview.getSettings().setJavaScriptEnabled(true); 
		  
		  return parentView;
	}
}
