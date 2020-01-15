package com.example.musicservice;

import com.example.musicservice.Music;

interface IMusicAIDLService{
        void play(); 
        void stop(); 
        void pause();
        void saveMusicInfo(in Music music); 
        List<Music> getAllMusic(); 
}