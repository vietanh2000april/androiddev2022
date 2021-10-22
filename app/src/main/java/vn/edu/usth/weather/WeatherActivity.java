package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.net.URL;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    private TabLayout mTabLayout;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager2 mViewPager2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setApplicationLocale("vn");
        setContentView(R.layout.activity_weather);


        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.thunder);
        mediaPlayer.start();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        String url = "";
//        MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(url));
//        mediaPlayer.prepare();
//        mediaPlayer.start();


        FragmentManager fm = getSupportFragmentManager();
        mViewPager2 = findViewById(R.id.view_pager_2);
        TabLayout mTabLayout = findViewById(R.id.sliding_tabs);
        mViewPagerAdapter = new ViewPagerAdapter(fm, getLifecycle());
        mViewPager2.setAdapter(mViewPagerAdapter);


        new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case Constants.FRAGMENT_HANOI:
                        tab.setText(getString(R.string.hanoi));
                        break;
                    case Constants.FRAGMENT_PARIS:
                        tab.setText(getString(R.string.paris));
                        break;
                    case Constants.FRAGMENT_TOULOUSE:
                        tab.setText(getString(R.string.toulouse));
                        break;
                }
            }
        }).attach();
        Log.i(TAG, "onCreate()");
    }


    private void setApplicationLocale(String locale) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(locale.toLowerCase()));
        } else {
            config.locale = new Locale(locale.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG, "onStart()");
    };
    @Override
    public void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart()");
    };
    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
    };
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause()");
    };
    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "onStop()");
    };
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    };

}