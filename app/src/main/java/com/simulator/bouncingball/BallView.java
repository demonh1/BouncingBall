package com.simulator.bouncingball;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by demonh1 on 02.03.15.
 */
public class BallView extends View implements Commons {

    private static final String TAG = "BallView";

    private int xMin = 0;
    private int xMax;
    private int yMin = 0;
    private int yMax;

    private int color;
    private int backgroundColor;

    private Ball ball = null;
    //private StatusMessage ms;

    private float acceleration;

    private AudioPlayer collision;


    public BallView(Context context,int color, int backgroundColor, float acceleration) {
        super(context);
        this.acceleration = acceleration;
        this.color = color;
        this.backgroundColor = backgroundColor;

        //ball = new Ball(Color.GREEN);
        //ms = new StatusMessage(Color.BLACK);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        xMax = w-1;
        yMax = h-1;
    }


    public void move() {

        // a = (v2-v1)/t
        // a*t = (v2-v1)
        // (a*t)+v1 = v2
        float v2 = acceleration * 0.1f + ball.getySpeed();

        int ii = BALL_RADIUS/2;

        ball.setySpeed(v2);
        ball.update();

        // Ball is out of bounds in y dimension
        if (ball.getY() > yMax) {
            ball.setY(yMax-ii);
            ball.setySpeed(-COEFFICIENT_OF_RESTITUTION * ball.getySpeed());

            collision.play();

        }
        else if (ball.getY() < 0) {
            ball.setY(ii);
            ball.setySpeed(-COEFFICIENT_OF_RESTITUTION * ball.getySpeed());

            collision.play();
        }


        // Ball is out of bounds in x dimension
        if (ball.getX() > xMax) {
            ball.setX(xMax-ii);
            ball.setxSpeed(-COEFFICIENT_OF_RESTITUTION * ball.getxSpeed());

            collision.play();
        }
        else if (ball.getX() < xMin) {
            ball.setX(xMin+ii);
            ball.setxSpeed(-COEFFICIENT_OF_RESTITUTION * ball.getxSpeed());

            collision.play();
        }

        // ball is rolling along the bottom
        if (ball.getY() == yMax - ii) {
            ball.setxSpeed(COEFFICIENT_OF_FRICTION * ball.getxSpeed());
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float currentX = event.getX();
        float currentY = event.getY();

        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return false;
        else
        {
            if(ball == null)
                ball = addBall(currentX, currentY, color);
            Log.d(TAG, "Action was DOWN");
        }

        return super.onTouchEvent(event);
    }

    private Ball addBall(float x, float y, int color) {
        Ball ball = new Ball (x, y, DEFAULT_SPEED_X, DEFAULT_SPEED_Y, color);
        return ball;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(backgroundColor);
        if(ball == null) { canvas.drawColor(backgroundColor); } /////
        else {
            //ms.draw(canvas);

            if(ball.getxSpeed() > TERM_SPEED) {
                ball.draw(canvas);

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        move();
                        //ms.sendInfo(ball);

                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) { e.printStackTrace(); }

                    }

                });
                thread.start();

                // ms.sendInfo(ball);
            }
            //ms.sendInfo(ball);
        }

        invalidate();

    }

    public void setAudioPlayer(AudioPlayer audioPlayer) {
        collision = audioPlayer;
    }

}

