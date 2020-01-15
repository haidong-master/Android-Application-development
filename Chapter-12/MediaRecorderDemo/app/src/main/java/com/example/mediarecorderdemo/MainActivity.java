package com.example.mediarecorderdemo;

import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button button_start;
    private Button button_stop;
    private Button button_Play;
    private MediaRecorder recorder;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);// 让界面横屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉界面标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 重新设置界面大小
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        button_start = (Button) this.findViewById(R.id.start_bt);
        button_stop = (Button) this.findViewById(R.id.end_bt);
        button_Play = (Button) this.findViewById(R.id.play_bt);
        button_stop.setOnClickListener(new AudioListerner());
        button_start.setOnClickListener(new AudioListerner());
        button_Play.setOnClickListener(new AudioListerner());
    }

    class AudioListerner implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == button_start) {
                initializeAudio();
            }
            if (v == button_stop) {
                recorder.stop();// 停止刻录
                // recorder.reset(); // 重新启动MediaRecorder.
                recorder.release(); // 刻录完成一定要释放资源
                // recorder = null;
            }
            if(v == button_Play){
                playAudio();
            }
        }

        private void initializeAudio() {
            recorder = new MediaRecorder();// new出MediaRecorder对象
            recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            // 设置MediaRecorder的音频源为麦克风
            recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            // 设置MediaRecorder录制的音频格式
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            // 设置MediaRecorder录制音频的编码为amr.貌似android就支持amr编码。
            recorder.setOutputFile("/sdcard/peipei.amr");
            // 设置录制好的音频文件保存路径
            try {
                recorder.prepare();// 准备录制
                recorder.start();// 开始录制
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void playAudio(){

        }
    }
}
