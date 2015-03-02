package com.simulator.bouncingball;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;

/**
 * Created by demonh1 on 02.03.15.
 */
public class SettingsActivity extends Activity {

    private static final String TAG = "SettingsActivity";
    private SeekBar alphaSeekBar;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private View colorView;
    private int color = Color.CYAN;
    private int backgroundColor = Color.WHITE;
    private int tempColor;

    private BallView v;

    private RadioGroup radioGroup;
    private float acceleration;

    private GridLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        layout = (GridLayout) getLayoutInflater().inflate(R.layout.settings, null);

        setContentView(layout);


        alphaSeekBar = (SeekBar) layout.findViewById(R.id.alphaSeekBar);
        redSeekBar = (SeekBar) layout.findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar) layout.findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar) layout.findViewById(R.id.blueSeekBar);
        colorView = layout.findViewById(R.id.colorView);

        // register SeekBar event listeners
        alphaSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        redSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        greenSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        blueSeekBar.setOnSeekBarChangeListener(colorChangedListener);

        // use current drawing color to set SeekBar values
        alphaSeekBar.setProgress(Color.alpha(color));
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));


    }

    public void onColorBall(View view) {
        tempColor = color;
    }

    public void onColorBackground(View view) {
        backgroundColor = color;
    }
    public void onOk(View view) {

        //////////
        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        switch (selectedId) {

            case R.id.radio_a0:
                acceleration = Commons.ACCELERATION;
                break;
            case R.id.radio_a1:
                acceleration = Commons.ACCELERATION_1;
                break;
            case R.id.radio_a2:
                acceleration = Commons.ACCELERATION_2;
                break;
        }

        if(tempColor == 0) tempColor = Color.CYAN;
        if(tempColor != color) {
            v = new BallView(this,tempColor,backgroundColor,acceleration);
            Log.d(TAG, "ColorTemp "+ tempColor);
            Log.d(TAG, "BColor " + backgroundColor);
        }
        else {
            v = new BallView(this,color,backgroundColor, acceleration);
            Log.d(TAG, "Color "+ color);
            Log.d(TAG, "BColor "+ backgroundColor);
        }

        setContentView(v);
        v.setAudioPlayer(getAudioPlayer(R.raw.bb_collision));
    }

    // for sound
    private AudioPlayer getAudioPlayer(int id) {
        return new AudioPlayer(this, id);
    }

    private SeekBar.OnSeekBarChangeListener colorChangedListener =
            new SeekBar.OnSeekBarChangeListener()
            {
                // display the updated color
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) // user, not program, changed SeekBar progress
                        color = Color.argb(alphaSeekBar.getProgress(),
                                redSeekBar.getProgress(), greenSeekBar.getProgress(),
                                blueSeekBar.getProgress());
                    colorView.setBackgroundColor(color);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar)
                {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)
                {
                }
            }; // end colorChanged


}
