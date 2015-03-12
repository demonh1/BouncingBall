package com.simulator.bouncingball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity {

    private static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSelectStart(View view){
        Log.v(TAG,"onSelectStart");
        startActivity(new Intent(this,BallActivity.class));
    }
    public void onSelectSettings(View view){
        Log.v(TAG,"onSelectSettings");
        startActivity(new Intent(this,SettingsActivity.class));
    }

}
