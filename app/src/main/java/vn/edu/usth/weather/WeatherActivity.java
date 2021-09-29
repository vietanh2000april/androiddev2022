package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
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