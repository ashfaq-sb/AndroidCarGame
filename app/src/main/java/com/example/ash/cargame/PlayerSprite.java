package com.example.ash.cargame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class PlayerSprite extends GameObject{
    public static final int BMP_ROW = 1;
    public static final int BMP_COL = 2;

    private int x = 0;
    private int y = 0;

    private GamePlay game;
    private Bitmap bmp;
    private final ID id;
    private GameObjectHandler handler;

    private int width;
    private int height;

    public static boolean moveLeft = false;
    public static boolean moveRight = false;

    private int xSpeed = 2;

    private int currentFrame = 0;

    public PlayerSprite(GamePlay game,Bitmap bmp ,int x, int y, ID id,GameObjectHandler handler){
        super(game, bmp, x, y, id);
        this.game = game;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
        this.id = id;
        //this.handler = handler;
        this.width = bmp.getWidth()/BMP_COL;
        this.height = bmp.getHeight()/BMP_ROW;
        this.handler = handler;


    }

    @Override
    public void update(){
        if(moveRight){
            if(x<game.getWidth()-width)
            x+=xSpeed;
        }
        if(moveLeft){
            if(x>0)
            x-=xSpeed;
        }
        y=game.getHeight() - height;
        currentFrame = ++currentFrame % BMP_COL;
        accident();
    }
    @Override
    public void onDraw(Canvas canvas){
        update();
        int srcX = currentFrame * width;
        int srcY = 0;
        Rect src = new Rect(srcX,srcY,srcX+width, srcY+height);
        Rect dst = new Rect(x,y, x+width, y+height);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    @Override
    public void fire() {

    }
    public void accident(){
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.BasicEnemy){
                if(getBounds().intersect(tempObject.getBounds())){
                    y--;
                }
            }
        }
    }
    @Override
    public Rect getBounds() {
        return new Rect(x,y,x+width,y+height);

    }
}
