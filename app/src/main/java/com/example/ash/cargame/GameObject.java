package com.example.ash.cargame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class GameObject {
    protected Bitmap bmp;
    protected GamePlay game;
    protected int x, y;
    protected ID id;
    protected int velX, velY;

    public GameObject(GamePlay game,Bitmap bmp, int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.game = game;
        this.bmp = bmp;
    }

    public abstract void fire();

    public abstract void update();

    public abstract void onDraw(Canvas canvas);

    public abstract Rect getBounds();



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }


}

