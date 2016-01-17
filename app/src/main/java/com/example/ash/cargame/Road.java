package com.example.ash.cargame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by ash on 26/10/2015.
 */
public class Road {

    private GamePlay game;
    private Bitmap bmp;

    private int width;
    private int height;
    private int x,y,my;
    public static boolean moveLeft = false;
    public static boolean moveRight = false;

    private int xSpeed = 2;

    private int currentFrame = 0;


    public Road(GamePlay game, Bitmap bmp1, int x, int y){

        this.bmp = bmp1;
        height = x;
        // System.out.println(bmp.getHeight());
        this.my = 1;
        this.x = x;
        this.y = y;
        this.game = game;
    }
    public void update(){
        if (bmp != null) {
           // System.out.println(game.getHeight()+" ===>"+y);
            y += my;
            if (y >= game.getHeight()) {
                y = 0;
                //System.out.println("if-----> " + y);
            }
            //currentFrame = ++currentFrame % 2;
            //System.out.println(y);
        }
    }
    public void onDraw(Canvas canvas){
       // update();
        if (bmp != null) {
            canvas.drawBitmap(bmp, x, y, null);
            if(y>0);
            {
                //System.out.println("asjkfnaskjfnaskf "+y);
                canvas.drawBitmap(bmp, x, y - game.getHeight() , null);
            }

        }

    }


}
