package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    private TabLayout mTabLayout;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager2 mViewPager2;
    ProgressBar progressBar;


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


        progressBar = findViewById(R.id.progressBar);



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
    // Initialize the contents of the Activity's options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

//            case R.id.action_favorite:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

        // thread and handler approach
    public void refreshButtonClicked(MenuItem item) {
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String content = msg.getData().getString("server_response");
                Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        };


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Bundle bundle = new Bundle();
                bundle.putString("server_response", "some sample json here");

                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


    public void refresh(MenuItem item) {
        new Refresh().execute();
    }

    class Refresh extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected Bitmap doInBackground(String... params) {
            // This is where the worker thread's code is executed
            // params are passed from the execute() method call
            for (int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                 running progress bar
                publishProgress(i);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            // This method is called in the main thread, so it's possible
            // to update UI to reflect the worker thread progress here.
            // In a network access task, this should update a progress bar
            // to reflect how many percent of data has been retrieved
            progressBar.setProgress(values[0] + 1);
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // This method is called in the main thread. After #doInBackground returns
            // the bitmap data, we simply set it to an ImageView using ImageView.setImageBitmap()
            Toast.makeText(WeatherActivity.this, "Successfully refreshed!", Toast.LENGTH_SHORT).show();
        }
    };

}
