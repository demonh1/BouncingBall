package com.simulator.bouncingball;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

/**
 * Created by demonh1 on 02.03.15.
 */
public class BallActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BallView v = new BallView(this, Color.CYAN, Color.WHITE, Commons.ACCELERATION);
        setContentView(v);
        v.setAudioPlayer(getAudioPlayer(R.raw.bb_collision));

    }

    private AudioPlayer getAudioPlayer(int id) {
        return new AudioPlayer(this, id);
    }
}
