package my.book.musicplayer.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;

import my.book.musicplayer.R;

public class XmlParse {
	/**
	 * �������������б�xml
	 * */
	public static List<Song> parseWebSongList(Context context){
		List<Song> data=null;
		Song song=null;
		XmlResourceParser parser = context.getResources().getXml(R.xml.web_list);
		try {
			int eventType=parser.getEventType();
			//ֱ����ȡ�ĵ�����
			while(eventType!=XmlResourceParser.END_DOCUMENT){
				switch (eventType) {
					//��ʼ��ȡ�ĵ�
					case XmlResourceParser.START_DOCUMENT:
						data=new ArrayList<Song>();
						break;
					//��ʼ��ȡ�ĵ���ǩ
					case XmlResourceParser.START_TAG:
						if(parser.getName().equals("song")){
							song=new Song();
							song.setId(parser.getAttributeIntValue(0, 0));
						}else if(parser.getName().equals("name")){
							song.setName(parser.nextText());
						}else if(parser.getName().equals("artist")){
							song.setArtist(new Artist(0, parser.nextText(), null));
						}else if(parser.getName().equals("album")){
							song.setAlbum(new Album(0, parser.nextText(), null));
						}else if(parser.getName().equals("displayName")){
							song.setDisplayName(parser.nextText());
						}else if(parser.getName().equals("mimeType")){
							song.setMimeType(parser.nextText());
						}else if(parser.getName().equals("netUrl")){
							song.setNetUrl(parser.nextText());
						}else if(parser.getName().equals("durationTime")){
							song.setDurationTime(Integer.valueOf(parser.nextText()));
						}else if(parser.getName().equals("size")){
							song.setSize(Integer.valueOf(parser.nextText()));
						}
						break;
					//��ȡ�ĵ���ǩ����
					case XmlResourceParser.END_TAG:
						if(parser.getName().equals("song")){
							data.add(song);
							song=null;
						}
						break;
				default:
					break;
				}
				//��ȡ�¸�Ԫ��
				eventType=parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
