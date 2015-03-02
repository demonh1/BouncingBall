package com.simulator.bouncingball;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by demonh1 on 02.03.15.
 */
public class AudioPlayer {

    private MediaPlayer mPlayer;
    private String name;

    private boolean playing = false;
    private boolean loop = false;

    public AudioPlayer(Context ctx, int resId) {
        name = ctx.getResources().getResourceName(resId);

        mPlayer = MediaPlayer.create(ctx, resId);

        mPlayer.setVolume(0.3f, 0.5f);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mp) {
                playing = false;
                if (isLoop()) {
                    System.out.println("AudioPlayer loop " + name);
                    mp.start();
                }
            }

        });

    }


    public synchronized void play() {
        if (playing)
            return;

        if (mPlayer != null) {
            playing = true;
            mPlayer.start();
        }
    }

    public synchronized void stop() {
        try {
            setLoop(false);
            if (playing) {
                playing = false;
                mPlayer.pause();
            }

        } catch (Exception e) {
            System.err.println("AduioClip::stop " + name + " " + e.toString());
        }
    }

    public synchronized void loop() {
        setLoop(true);
        playing = true;
        mPlayer.start();

    }

    public void release() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


    public boolean isLoop() {
        return loop;
    }


    public void setLoop(boolean loop) {
        this.loop = loop;
    }

}
