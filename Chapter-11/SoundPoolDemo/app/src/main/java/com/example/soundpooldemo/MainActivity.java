package com.example.soundpooldemo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    /*
     * 1. 定义资源文件
     * 在res资源目录中新建一个raw子目录，将需要加载的音频文件放入其中，比如加载了shiqishidaibeijingyinyue.mp3
     */
    private SoundPool soundPool;
    private int soundId;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. 实现soundPool对象
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0); // 分别对应声音池数量，AudioManager.STREAM_MUSIC
        // 和 0

        // 3. 使用soundPool加载声音，该操作位异步操作，如果资源很大，需要一定的时间
        soundId = soundPool.load(this, R.raw.mp3, 1);

        // 4. 为声音池设定加载完成监听事件
        soundPool
                .setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool,
                                               int sampleId, int status) {
                        flag = true; // 表示加载完成
                    }
                });
    }

    // 5.播放操作
    public void click(View view) {
        if (flag) {
            // 播放声音池中的文件, 可以指定播放音量，优先级 声音播放的速率
            soundPool.play(soundId, 1.0f, 0.5f, 1, 0, 1.0f);
        } else {
            Toast.makeText(this, "正在加载，请稍后", 1).show();
        }
    }

    // 6.使用完全后，应该释放资源
    @Override
    protected void onDestroy() {
        soundPool.release();
        soundPool = null;
        super.onDestroy();
    }
}

