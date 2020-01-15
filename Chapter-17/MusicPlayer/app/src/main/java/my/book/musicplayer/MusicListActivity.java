package my.book.musicplayer;

import my.book.musicplayer.adapter.MusicListAdapter;
import my.book.musicplayer.service.MusicService;
import my.book.musicplayer.tool.Contsant;
import my.book.musicplayer.tool.XfDialog;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.AudioColumns;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.app.Fragment;
import android.text.method.DigitsKeyListener;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import my.book.musicplayer.R;

public class MusicListActivity extends Fragment {

	private ListView listview;
	private int _ids[];        // ��������ļ���id����
	private String _titles[];  // ��������ļ��ı�������
	private String _artists[]; // ������������ҵı�������
	private String[] _path;  // �������·��ı�������
	private String[] _times;// �����ʱ��ı�������
	private String[] _album;// ���ר���ı�������
	private int _sizes[];// ����ļ���С�ı�������
	private String[] _displayname;//�����Ƶı�������
	private Menu xmenu;//�Զ���˵�
	private int num;//numȷ��һ����ʶ
	private int c;//ͬ��
	private LayoutInflater inflater;//װ�ز���
	private LayoutParams params;
	private Toast toast;//��ʾ
	/**�����ʶ����**/
	public static final int Ringtone = 0;
	public static final int Alarm = 1;
	public static final int Notification = 2;
	private TextView timers;//��ʾ����ʱ������
	private Timers timer;//����ʱ�ڲ�����
	private ImageButton plays_btn;
	private int position;
	MusicListAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View parentView = inflater.inflate(R.layout.music_list, container, false);
		
		/** ѡ���������¼� ***/
		listview = (ListView) parentView.findViewById(R.id.local_music_list);
		listview.setOnItemClickListener(new MusicListOnClickListener());
		listview.setOnCreateContextMenuListener(new MusicListContextListener());
		timers=(TextView) parentView.findViewById(R.id.timer_clock);
		inflater = (LayoutInflater) getActivity().getSystemService(getActivity().getApplicationContext().LAYOUT_INFLATER_SERVICE);
		params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		plays_btn=(ImageButton) parentView.findViewById(R.id.plays_btn);
		plays_btn.setOnClickListener(listeners);
		//LoadMenu();
		ShowMp3List();
		
		return parentView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
	}

	/**
	 * ��ʾMP3��Ϣ,����_ids���������������ļ���_ID������ȷ������Ҫ������һ�׸���_titles���������������ʾ�ڲ��Ž��棬
	 * ��_path��������ļ���·����ɾ���ļ�ʱ���õ�����
	 */
	private void ShowMp3List() {
		// ���α����ý����ϸ��Ϣ
		Cursor cursor = getActivity().getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaColumns.TITLE,// ���⣬�α��0��ȡ
						AudioColumns.DURATION,// ����ʱ��,1
						AudioColumns.ARTIST,// ������,2
						BaseColumns._ID,// id,3
						MediaColumns.DISPLAY_NAME,// ��ʾ���,4
						MediaColumns.DATA,// ��ݣ�5
						AudioColumns.ALBUM_ID,// ר�����ID,6
						AudioColumns.ALBUM,// ר��,7
						MediaColumns.SIZE }, null, null, AudioColumns.ARTIST);// ��С,8
		/** �ж��α��Ƿ�Ϊ�գ���Щ�ط���ʹû������Ҳ�ᱨ�쳣�������α겻�ȶ������в����ͳ�����,��Σ�����û�û�����ֵĻ����������Ը�֪�û�û���������û���ӽ�ȥ */
		if (cursor!= null && cursor.getCount() == 0) {
			final XfDialog xfdialog = new XfDialog.Builder(getActivity()).setTitle("��ʾ:").setMessage("����ֻ�δ�ҵ�����,���������").setPositiveButton("ȷ��", null).create();
			xfdialog.show();
			return;

		}
		/** ���α��Ƶ���һλ **/
		cursor.moveToFirst();
		/** �ֱ𽫸�����������ʵ�� **/
		_ids = new int[cursor.getCount()];//
		_titles = new String[cursor.getCount()];
		_artists = new String[cursor.getCount()];
		_path = new String[cursor.getCount()];
		_album = new String[cursor.getCount()];
		_times = new String[cursor.getCount()];
		_displayname = new String[cursor.getCount()];
		_sizes = new int[cursor.getCount()];
		/**
		 * �����ȡ·���ĸ�ʽ��_path[i]=c.geString,Ϊʲô��ôд������ΪMediaStore.Audio.Media.DATA�Ĺ�ϵ
		 * �õ������ݸ�ʽΪ/mnt/sdcard/[���ļ�����/]�����ļ����������Ҫ�õ�����/sdcard/[���ļ�����]�����ļ���
		 */
		for (int i = 0; i < cursor.getCount(); i++) {
			_ids[i] = cursor.getInt(3);
			_titles[i] = cursor.getString(0);
			_artists[i] = cursor.getString(2);
			_path[i] = cursor.getString(5).substring(4);
			/**************** ������Ϊ�ṩ������ϸ��Ϣ����ɵ� ***************************/
			_album[i] = cursor.getString(7);
			_times[i] = toTime(cursor.getInt(1));
			_sizes[i] = cursor.getInt(8);
			_displayname[i] = cursor.getString(4);
			 cursor.moveToNext();
		  /*** һֱ���α������� **/
		}
		listview.setAdapter(new MusicListAdapter(getActivity(), cursor));

	}
	/**
	 * ʱ���ת��
	 */
	public String toTime(int time) {

		time /= 1000;
		int minute = time / 60;
		int second = time % 60;
		minute %= 60;
		/** ���ؽ����string��format������ʱ��ת�����ַ����� **/
		return String.format("%02d:%02d", minute, second);
	}
	

	/** �����б���Ӽ����������֮�󲥷����� */
	public class MusicListOnClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long id) {
			playMusic(position);
		}
	}
/**
 * ����Listview�󵯳��˵�
 */
	private class MusicListContextListener implements OnCreateContextMenuListener {

		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo info) {
			//SongItemDialog();
			final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) info;
			num = menuInfo.position;
		}

	}
/**
 * ���Position��������
 */
	public void playMusic(int position) {
		Intent intent = new Intent(getActivity(), PlayMusicActivity.class);
		intent.putExtra("_ids", _ids);
		intent.putExtra("_titles", _titles);
		intent.putExtra("_artists", _artists);
		intent.putExtra("position", position);
		startActivity(intent);
		getActivity().finish();
	}

	/**
	 * �û������б���߰�ס���ұߵĵ�����η�����¼�
	 
	private void SongItemDialog() {
		String[] menustring = new String[] { "���Ŵ�����", "��������Ϊ����", "�鿴�ø�������" };
		ListView menulist = new ListView(getActivity());
		menulist.setCacheColorHint(Color.TRANSPARENT);
		menulist.setDividerHeight(1);
		menulist.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.dialog_menu_item, R.id.text1, menustring));
		menulist.setLayoutParams(new LayoutParams(Contsant.getScreen(getActivity())[0] / 2,LayoutParams.WRAP_CONTENT));

		final XfDialog xfdialog = new XfDialog.Builder(getActivity()).setTitle(R.string.opertion_file).setView(menulist).create();
		xfdialog.show();
		menulist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				xfdialog.cancel();
				xfdialog.dismiss();
				if (position==0) {
				    playMusic(num);	
				}else if (position==1) {
					SetRing();
				}else if (position==2) {
					ShowMusicInfo(num);
				}

			}
		});
	}
*/
	
	/**
	 * ��ʾ������ϸ��Ϣ
	 */
	private void ShowMusicInfo(int location) {
		View view = inflater.inflate(R.layout.song_detail, null);
		((TextView) view.findViewById(R.id.tv_song_title)).setText(_titles[num]);
		((TextView) view.findViewById(R.id.tv_song_artist)).setText(_artists[num]);
		((TextView) view.findViewById(R.id.tv_song_album)).setText(_album[num]);
		((TextView) view.findViewById(R.id.tv_song_filepath)).setText(_path[num]);
		((TextView) view.findViewById(R.id.tv_song_duration)).setText(_times[num]);
		((TextView) view.findViewById(R.id.tv_song_format)).setText(Contsant.getSuffix(_displayname[num]));
		((TextView) view.findViewById(R.id.tv_song_size)).setText(Contsant.formatByteToMB(_sizes[num]) + "MB");
		new XfDialog.Builder(getActivity()).setTitle(R.string.music_info).setNeutralButton(R.string.confrim, null).setView(view).create().show();
	}
	/**
	 * ���߷���
	 */
   private void Sleep(){
	   final EditText edtext = new EditText(getActivity());
	   edtext.setText("5");//���ó�ʼֵ
		edtext.setKeyListener(new DigitsKeyListener(false, true));
		edtext.setGravity(Gravity.CENTER_HORIZONTAL);//���ð���λ��
		edtext.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//��������
		edtext.setTextColor(Color.BLUE);//������ɫ
		edtext.setSelection(edtext.length());//����ѡ��λ��
		edtext.selectAll();//ȫ��ѡ��
	    new XfDialog.Builder(getActivity()).setTitle("������ʱ��").setView(edtext).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				dialog.cancel();
				/**�������С��2���ߵ���0���֪�û�**/
				if (edtext.length() <= 2 && edtext.length() != 0) {
					if (".".equals(edtext.getText().toString())) {
						toast = Contsant.showMessage(toast,getActivity(), "�������������������λ����.");
					} else {
						final String time = edtext.getText().toString();
						long Money = Integer.parseInt(time);
						long cX = Money * 60000;
						timer= new Timers(cX, 1000);
					    timer.start();//����ʱ��ʼ
						toast = Contsant.showMessage(toast,getActivity(), "����ģʽ����!��" + String.valueOf(time)+ "���Ӻ�رճ���!");
						timers.setVisibility(View.INVISIBLE);
						timers.setVisibility(View.VISIBLE);
						timers.setText(String.valueOf(time));
					}

				} else {
					Toast.makeText(getActivity(), "�����뼸����",Toast.LENGTH_SHORT).show();
				}
				
			}
		}).setNegativeButton(R.string.cancel, null).show();
		
}
 /**
  * ����һ������ʱ
  */
 	private class Timers extends CountDownTimer{

 		public Timers(long millisInFuture, long countDownInterval) {
 			super(millisInFuture, countDownInterval);
 		}

 		@Override
 		public void onFinish() {
 			 if (c==0) {
 				exit();
 				getActivity().finish();
 				onDestroy();
 			}else {
 				getActivity().finish();
 				onDestroy();
 				android.os.Process.killProcess(android.os.Process.myPid());
 			}
 		}

 		@Override
 		public void onTick(long millisUntilFinished) {
 			timers.setText("" + millisUntilFinished / 1000 / 60 + ":"+ millisUntilFinished / 1000 % 60);
 			// ������������9 ˵������2λ����,����ֱ�����롣����С�ڵ���9 �Ǿ���1λ������ǰ���һ��0
 			String abc = (millisUntilFinished / 1000 / 60) > 9 ? (millisUntilFinished / 1000 / 60)+ "": "0" + (millisUntilFinished / 1000 / 60);
 			String b = (millisUntilFinished / 1000 % 60) > 9 ? (millisUntilFinished / 1000 % 60)+ "": "0" + (millisUntilFinished / 1000 % 60);
 			timers.setText(abc + ":" + b);
 			timers.setVisibility(View.GONE);
 		}
 		
 	}
    /**
      * �˳����򷽷�
      */
 	private void exit(){
 		Intent mediaServer = new Intent(getActivity(),MusicService.class);
 		getActivity().stopService(mediaServer);
 		getActivity().finish();
 	}
 	/**
 	 * ����ť�����¼�
 	 */
	private OnClickListener listeners = new OnClickListener(){

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.plays_btn:
				Intent intent = new Intent(getActivity(),PlayMusicActivity.class);
				intent.putExtra("_ids", _ids);
				intent.putExtra("_titles", _titles);
				intent.putExtra("_artists", _artists);
				intent.putExtra("position", position);
				startActivity(intent);
				getActivity().finish();
				break;

			case R.id.ibtn_song_list_item_menu:
				//SongItemDialog();
				break;
			}
			
		}
		
	};	
}
