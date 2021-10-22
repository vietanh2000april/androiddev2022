package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    private TabLayout mTabLayout;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager2 mViewPager2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
//        WeatherAndForecastFragment wff = new WeatherAndForecastFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.activity_weather, wff).commit();

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
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Log.i(TAG, "onCreate()");
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