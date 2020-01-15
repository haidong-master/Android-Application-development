package my.book.musicplayer;

import my.book.musicplayer.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends FragmentActivity {

    private final Class[] fragments = {MusicListActivity.class,
            FavroiteActivity.class,
            MusicOnlineActivity.class};
    private FragmentTabHost mTabHost;
    private RadioGroup mTabRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        int count = fragments.length;
        for (int i = 0; i < count; i++) {
            TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
            mTabHost.addTab(tabSpec, fragments[i], null);
        }

        mTabRg = (RadioGroup) findViewById(R.id.tab_group);
        mTabRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.local:
                        mTabHost.setCurrentTab(0);
                        break;
                    case R.id.fav:
                        mTabHost.setCurrentTab(1);

                        break;
                    case R.id.online:
                        mTabHost.setCurrentTab(2);
                        break;

                    default:
                        break;
                }
            }
        });

        mTabHost.setCurrentTab(0);
    }

    @Override
    public void onBackPressed() {
    }
}
