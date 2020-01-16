package my.book.musicplayer.adapter;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.AudioColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import my.book.musicplayer.R;

public class MusicListAdapter extends BaseAdapter {
	private Context mcontext;// ������
	private Cursor mcursor;// �α�
	private int pos=-1;
	public MusicListAdapter(Context context, Cursor cursor) {
		mcontext = context;
		mcursor = cursor;
	}
	@Override
	public int getCount() {
		return mcursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder = null;
		if (convertView == null) {
			viewholder = new ViewHolder();
			convertView = LayoutInflater.from(mcontext).inflate(R.layout.music_list_item, null);
			viewholder.images = (ImageView) convertView.findViewById(R.id.images_album);
			viewholder.music_names = (TextView) convertView.findViewById(R.id.musicname);
			viewholder.singers = (TextView) convertView.findViewById(R.id.singer);
			viewholder.times = (TextView) convertView.findViewById(R.id.time);
			viewholder.song_list_item_menu = (ImageButton) convertView.findViewById(R.id.ibtn_song_list_item_menu);
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		mcursor.moveToPosition(position);
		if (position==pos) {
			viewholder.images.setImageResource(R.drawable.isplaying);
		}else {
			Bitmap bitmap = getArtwork(mcontext, mcursor.getInt(3),mcursor.getInt(mcursor.getColumnIndex(AudioColumns.ALBUM_ID)),true);
			viewholder.images.setImageBitmap(bitmap);
		}
		viewholder.music_names.setText(mcursor.getString(0));
		viewholder.singers.setText(mcursor.getString(2));
		viewholder.times.setText(toTime(mcursor.getInt(1)));
		return convertView;

	}
	public class ViewHolder {
		public ImageView images;
		public TextView music_names;
		public TextView singers;
		public TextView times;
		public ImageButton song_list_item_menu;
	}
	public void setItemIcon(int position){
		pos = position;
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

	/**
	 * �����Ǹ���ŵ�ʱ����ʾר��ͼƬ�����б?ͬ,����ʱͼƬҪ������cam�Ǹ�����д���ʵ�ͼƬ��
	 */
	public static Bitmap getArtwork(Context context, long song_id,
			long album_id, boolean allowdefault) {
		if (album_id < 0) {

			if (song_id >= 0) {
				Bitmap bm = getArtworkFromFile(context, song_id, -1);
				if (bm != null) {
					return bm;
				}
			}
			if (allowdefault) {
				return getDefaultArtwork(context);
			}
			return null;
		}
		ContentResolver res = context.getContentResolver();
		Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);
		if (uri != null) {
			InputStream in = null;
			try {
				in = res.openInputStream(uri);
				BitmapFactory.Options options = new BitmapFactory.Options();
				/** ��ָ��ԭʼ��С **/
				options.inSampleSize = 1;
				/** ֻ���д�С�ж� **/
				options.inJustDecodeBounds = true;
				/** ���ô˷����õ�options�õ�ͼƬ�Ĵ�С **/
				BitmapFactory.decodeStream(in, null, options);
				/** ���ǵ�Ŀ��������N pixel�Ļ�������ʾ�� ������Ҫ����computeSampleSize�õ�ͼƬ���ŵı��� **/
				/** �����targetΪ800�Ǹ��Ĭ��ר��ͼƬ��С�����ģ�800ֻ�ǲ������ֵ���������������Ľ�� **/
				options.inSampleSize = computeSampleSize(options, 30);
				/** ���ǵõ������ŵı������ڿ�ʼ��ʽ����BitMap��� **/
				options.inJustDecodeBounds = false;
				options.inDither = false;
				options.inPreferredConfig = Bitmap.Config.ARGB_8888;
				in = res.openInputStream(uri);
				return BitmapFactory.decodeStream(in, null, options);
			} catch (FileNotFoundException ex) {

				Bitmap bm = getArtworkFromFile(context, song_id, album_id);
				if (bm != null) {
					if (bm.getConfig() == null) {
						bm = bm.copy(Bitmap.Config.RGB_565, false);
						if (bm == null && allowdefault) {
							return getDefaultArtwork(context);
						}
					}
				} else if (allowdefault) {
					bm = getDefaultArtwork(context);
				}
				return bm;
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (IOException ex) {
				}
			}
		}

		return null;
	}

	private static Bitmap getArtworkFromFile(Context context, long songid,
			long albumid) {
		Bitmap bm = null;
		if (albumid < 0 && songid < 0) {
			throw new IllegalArgumentException(
					"Must specify an album or a song id");
		}
		try {

			BitmapFactory.Options options = new BitmapFactory.Options();

			FileDescriptor fd = null;
			if (albumid < 0) {
				Uri uri = Uri.parse("content://media/external/audio/media/"
						+ songid + "/albumart");
				ParcelFileDescriptor pfd = context.getContentResolver()
						.openFileDescriptor(uri, "r");
				if (pfd != null) {
					fd = pfd.getFileDescriptor();
				}
			} else {
				Uri uri = ContentUris.withAppendedId(sArtworkUri, albumid);
				ParcelFileDescriptor pfd = context.getContentResolver()
						.openFileDescriptor(uri, "r");
				if (pfd != null) {
					fd = pfd.getFileDescriptor();
				}
			}
			options.inSampleSize = 1;
			// ֻ���д�С�ж�
			options.inJustDecodeBounds = true;
			// ���ô˷����õ�options�õ�ͼƬ�Ĵ�С
			BitmapFactory.decodeFileDescriptor(fd, null, options);
			// ���ǵ�Ŀ������800pixel�Ļ�������ʾ��
			// ������Ҫ����computeSampleSize�õ�ͼƬ���ŵı���
			options.inSampleSize = 500;
			// OK,���ǵõ������ŵı������ڿ�ʼ��ʽ����BitMap���
			options.inJustDecodeBounds = false;
			options.inDither = false;
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;

			// ���options�����������Ҫ���ڴ�
			bm = BitmapFactory.decodeFileDescriptor(fd, null, options);
		} catch (FileNotFoundException ex) {

		}

		return bm;
	}

	/** ���������ͼƬ�Ĵ�С�����жϣ����õ����ʵ����ű������2��1/2,3��1/3 **/
	static int computeSampleSize(BitmapFactory.Options options, int target) {
		int w = options.outWidth;
		int h = options.outHeight;
		int candidateW = w / target;
		int candidateH = h / target;
		int candidate = Math.max(candidateW, candidateH);
		if (candidate == 0)
			return 1;
		if (candidate > 1) {
			if ((w > target) && (w / candidate) < target)
				candidate -= 1;
		}
		if (candidate > 1) {
			if ((h > target) && (h / candidate) < target)
				candidate -= 1;
		}
		return candidate;
	}

	private static Bitmap getDefaultArtwork(Context context) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.RGB_565;
		return BitmapFactory.decodeStream(context.getResources().openRawResource(R.drawable.music), null, opts);
	}

	private static final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

}
