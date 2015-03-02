package com.simulator.bouncingball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by demonh1 on 02.03.15.
 */
public class Ball {

    private static final float radius = Commons.BALL_RADIUS;

    private float x, y;
    private float speedX;
    private float speedY;

    private RectF bounds;
    private Paint paint;

    private static final float DEFAULT_SPEED_X = Commons.DEFAULT_SPEED_X;
    private static final float DEFAULT_SPEED_Y = Commons.DEFAULT_SPEED_Y;

    public Ball(float x, float y, float xSpeed, float ySpeed, int color){
        this.x = x;
        this.y = y;
        speedX = xSpeed;
        speedY = ySpeed;

        bounds = new RectF();
        paint = new Paint();
        paint.setColor(color);

    }

    public Ball(int color) {

        speedX = DEFAULT_SPEED_X;
        speedY = DEFAULT_SPEED_Y;

        x = radius + 20;
        y = radius + 20;

        bounds = new RectF();
        paint = new Paint();
        paint.setColor(color);
    }

    public void update() {
        this.x += speedX;
        this.y += speedY;
    }


    public void draw(Canvas canvas) {
        bounds.set(x-radius, y-radius, x+radius, y+radius);
        canvas.drawOval(bounds, paint);
    }

    // ====================== setters & getters ================
    // =========================================================
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getxSpeed() {
        return speedX;
    }

    public void setxSpeed(float speedX) {
        this.speedX = speedX;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getySpeed() {
        return speedY;
    }

    public void setySpeed(float speedY) {
        this.speedY = speedY;
    }


}

