package com.example.ash.cargame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BasicEnemy extends GameObject{

    private static final int BMP_COL = 2;
    private static final int BMP_ROW = 1;
    private int width,height;
    public Bitmap bmp;
    private int currentFrame = 0;
    private GameObjectHandler handler;
    // private int x = 0;
    //private int y = 0;



    public BasicEnemy(GamePlay game,Bitmap bmp ,int x, int y, ID id, GameObjectHandler handler) {
        super(game,bmp , x, y, id);
        this.handler = handler;
        this.game = game;
        this.bmp = bmp;
        this.width = bmp.getWidth()/BMP_COL;
        this.height = bmp.getHeight()/BMP_ROW;
        velX =1;
        velY = 1;
        this.handler = handler;
    }

    @Override
    public void fire() {



    }

    @Override
    public void update() {
        currentFrame = ++currentFrame % BMP_COL;
        motion();
        accident();
    }

    @Override
    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = 0;
       // Rect src = new Rect(null);
       Rect src = new Rect(srcX,srcY,srcX+width, srcY+height);
        Rect dst = new Rect(x,y, x+width, y+height);
        canvas.drawBitmap(bmp, src, dst, null);

       // canvas.drawRoundRect(new RectF(),20,30,null);
    }

    @Override
    public Rect getBounds() {
        return new Rect(x,y,x+width,y+height);
        //return  new Rect(getX(),getY(),getX()+GamePlay.);
    }

    public void accident(){
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){

                if(getBounds().intersect(tempObject.getBounds())){
                    handler.removeObject(this);
                    GamePlay.enemyCounter--;
                    System.out.println("asdsadsdasaddsasda "+GamePlay.enemyCounter);
                }
            }
        }
    }

    public void motion() {

        y += velY;

        if (y > game.getHeight()  ) {
            velY = 0;
            velX = 1;
            x += velX;
            // System.out.println("Left " + x + " " + y);
            //System.out.println(game.getWidth());
        }
        if(x>game.getWidth()-500){
            velY = -1;
            velX = 0;
           // y += -velY;
           // System.out.println(x);
        }
        if(y< -800){
            velY =0;
            velX= -1;
            x+=velX;

        }
        if(x<=100){
            velX =0;
            velY =1;
        }
//        if ((x < -50)) {
//            velY = 0;
//            // x=50;
//            velY = 1;
//            y += -velY;
//            //System.out.println("Up " + x + " " + y);
//        } else if (y > game.getHeight() ) {
//            velY = 0;
//            velX = -1;
//            x += velX;
//            // System.out.println("Left " + x + " " + y);
//
//        }
//        if (x > game.getHeight() - 130) {
//            // dy=1;
//            velX = 0;
//            velY = -1;
//            y -= velY;
//            // System.out.println("Down" + x + " " + y);
//        } else if ((y < game.getHeight() - 200) && ((x < -150)) || (y < -250)) {
//
//                velY = 0;
//                velX = -1;
//                x -= velX;
//                System.out.println("Right " + x + " " + y);
//            }

    }


}
