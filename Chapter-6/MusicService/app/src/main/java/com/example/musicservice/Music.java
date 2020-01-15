package com.example.musicservice;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable { 

    private String music_name; 
    private String music_author; 
    private int    play_time;
    public Music() {} 

    public Music(Parcel pl){ 
		music_name = pl.readString(); 
		music_author = pl.readString(); 
		play_time = pl.readInt(); 
    } 

    public String getMusicName() { 
    	return music_name; 
    } 

    public void settMusicName(String name) { 
    	this.music_name = name; 
    } 

    public String getMusicAuthor() { 
            return music_author; 
    } 

    public void setMusicAuthor(String music_author) { 
            this.music_author = music_author; 
    } 

    public int getPlayTime() { 
            return play_time; 
    } 

    public void setPlayTime(int play_time) { 
            this.play_time = play_time; 
    } 

    @Override 
    public int describeContents() { 
            return 0; 
    } 

    @Override 
    public void writeToParcel(Parcel dest, int flags) { 
            dest.writeString(music_name); 
            dest.writeString(music_author); 
            dest.writeInt(play_time); 
    } 

    public static final Parcelable.Creator<Music> CREATOR = new Parcelable.Creator<Music>() { 

            @Override 
            public Music createFromParcel(Parcel source) { 
                    return new Music(source); 
            } 

            @Override 
            public Music[] newArray(int size) { 
                    return new Music[size]; 
            } 

    };

}