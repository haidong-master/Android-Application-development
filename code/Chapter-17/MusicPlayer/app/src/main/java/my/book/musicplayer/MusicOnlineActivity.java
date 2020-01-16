package my.book.musicplayer;

import my.book.musicplayer.net.NetMusicAdapter;
import my.book.musicplayer.net.XmlParse;
import my.book.musicplayer.tool.Contsant;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import my.book.musicplayer.R;

public class MusicOnlineActivity extends Fragment {
	private ListView listview;
    private Toast toast;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View parentView = inflater.inflate(R.layout.net_music, container, false);
		
		
		listview = (ListView) parentView.findViewById(R.id.net_list);
		
		if (!Contsant.getNetIsAvailable(getActivity())) {
		 toast=Contsant.showMessage(toast, getActivity(), "�������ӳ�ʱ..����");
		}
		listview.setAdapter(new NetMusicAdapter(getActivity(), XmlParse.parseWebSongList(getActivity())));
		return parentView;
		
	}
}
