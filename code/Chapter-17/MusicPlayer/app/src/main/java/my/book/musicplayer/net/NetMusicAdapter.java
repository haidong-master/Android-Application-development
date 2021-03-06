package my.book.musicplayer.net;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import my.book.musicplayer.R;

public class NetMusicAdapter extends BaseAdapter {
	private List<Song> data;
	private Context context;
	private int[] playerInfo=new int[2];//0:playerId,1:playerstate
	private ItemListener mItemListener;

	public NetMusicAdapter(Context context,List<Song> data){
		this.context=context;
		this.data=data;
		playerInfo[0]=-1;
		playerInfo[1]=-1;
	}
	
	public NetMusicAdapter setItemListener(ItemListener mItemListener){
		this.mItemListener=mItemListener;
		return this;
	}
	
	public void setPlayerInfo(int[] playerInfo){
		this.playerInfo=playerInfo;
		notifyDataSetChanged();
	}
	
	public void setPlayerState(int playerState){
		this.playerInfo[1]=playerState;
		notifyDataSetChanged();
	}
	
	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.song_list_item_web, null);
			viewHolder.tv_web_list_item_number=(TextView)convertView.findViewById(R.id.tv_web_list_item_number);
			viewHolder.tv_web_list_item_top=(TextView)convertView.findViewById(R.id.tv_web_list_item_top);
			viewHolder.tv_web_list_item_bottom=(TextView)convertView.findViewById(R.id.tv_web_list_item_bottom);
			viewHolder.ibtn_web_list_item_download=(Button)convertView.findViewById(R.id.ibtn_web_list_item_download);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder)convertView.getTag();
		}
		final Song song=data.get(position);
			viewHolder.tv_web_list_item_number.setText((position+1)+"");
			viewHolder.tv_web_list_item_number.setBackgroundResource(0);
		
		
		viewHolder.tv_web_list_item_top.setText(song.getName());
		viewHolder.tv_web_list_item_top.setTag(song.getId());
		viewHolder.tv_web_list_item_bottom.setText(song.getArtist().getName());
		
		viewHolder.ibtn_web_list_item_download.setTag(song.getNetUrl());//�ļ�·��
		
		viewHolder.ibtn_web_list_item_download.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(mItemListener!=null){
					mItemListener.onDownLoad(song);
				}
			}
		});

		viewHolder.ibtn_web_list_item_download.setFocusable(false);
		viewHolder.ibtn_web_list_item_download.setFocusableInTouchMode(false);
		return convertView;
	}

	public class ViewHolder{
		public TextView tv_web_list_item_number;
		public TextView tv_web_list_item_top;
		public TextView tv_web_list_item_bottom;
		public Button ibtn_web_list_item_download;
	}
	
	public interface ItemListener{
		void onDownLoad(Song song);
	}
	
}
