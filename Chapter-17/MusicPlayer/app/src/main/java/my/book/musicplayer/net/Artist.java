package my.book.musicplayer.net;

import java.io.Serializable;

/**
 * ����[0:δ֪����]
 * */
public class Artist implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;//id
	private String name;//�������
	private String picPath;//����ͼƬ
	
	public Artist(){
	}

	public Artist(int id,String name,String picPath){
		this.id=id;
		this.name=name;
		this.picPath=picPath;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
}
