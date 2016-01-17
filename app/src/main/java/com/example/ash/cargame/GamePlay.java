package com.example.ash.cargame;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by Belal Khan on 10/27/2014.
 */
public class GamePlay extends SurfaceView {


    long timer = System.currentTimeMillis();
    private Bitmap player,enemy,enemy2;
    private SurfaceHolder holder;
    private GameThread gameThread;
    private PlayerSprite myPlayer;
   // private int x,y,width,height;
    private Bitmap bmpRight;
    private Bitmap bmpLeft;
    private Road road;
    private GameObjectHandler handler;
    private boolean moveRight;
    private boolean moveLeft;
    Random r;


    private Bitmap background;
    public static int enemyCounter =0;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public GamePlay(Context context){
        super(context);
        holder = getHolder();
        gameThread = new GameThread(this);
        holder.addCallback(new SurfaceHolder.Callback(){

            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                gameThread.startGame(true);
                gameThread.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
             //   gameThread.stop();
            }
        });

        handler = new GameObjectHandler();
        player = BitmapFactory.decodeResource(getResources(), R.drawable.scar5);

        enemy = BitmapFactory.decodeResource(getResources(), R.drawable.struck);

        bmpLeft = BitmapFactory.decodeResource(getResources(), R.drawable.left);
        bmpRight = BitmapFactory.decodeResource(getResources(), R.drawable.right);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.road3);


        road = new Road(this,background, -55 ,0);
//       try {
//           background.setWidth(getWidth());
//           background.setHeight(getHeight());
//       }catch (Exception e){
//           e.printStackTrace();
//       }

        handler.addObject(new PlayerSprite(this,player,0,0,ID.Player,handler));

    }

    @Override
    protected void onDraw(Canvas canvas){
        int x = getWidth()-bmpRight.getWidth();
        int y = (getHeight()/2)-(bmpRight.getHeight()/2);
        road.onDraw(canvas);
        //canvas.drawBitmap(background , -55 ,30 ,null);
        canvas.drawBitmap(bmpRight, x,y, null);
        canvas.drawBitmap(bmpLeft, 0, y, null);
        //myPlayer.onDraw(canvas);
        handler.render(canvas);
    }
    public void update() {
        r = new Random();
    road.update();
    handler.tick();
        if (System.currentTimeMillis() - timer > 5000) {

            //System.out.println("asdasdasdasd" + );
            if(enemyCounter <3){
                handler.addObject(new BasicEnemy(this, enemy, r.nextInt(getWidth() -100), -450, ID.BasicEnemy, handler));
                enemyCounter++;
                System.out.println("asdsadsdasaddsasda "+r.nextInt(2));

            }

            timer+= 5000;
        }
    }


    protected void checkMovement(float x2, float y2){
        int xRight = getWidth()  - bmpRight.getWidth();
        int xLeft = 0 ;

        int y = (getHeight()/2)-(bmpRight.getHeight()/2);

        int width = bmpRight.getWidth();
        int height = bmpRight.getHeight();

        if(x2>xRight && x2<xRight + width && y2>y && y2<y+height){
            moveRight=true;
            moveLeft=false;
        }
        else if(x2>xLeft && x2<xLeft+ width && y2>y && y2<y+height){
            moveRight=false;
            moveLeft=true;
        }else{

            moveRight= false;
            moveLeft= false;
        }


    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(event.getAction()==MotionEvent.ACTION_DOWN){

            checkMovement(event.getX(), event.getY());
            if(moveRight){
                PlayerSprite.moveRight=true;
                PlayerSprite.moveLeft=false;
            }
            if(moveLeft){
                PlayerSprite.moveRight=false;
                PlayerSprite.moveLeft=true;
            }
        }

        if(event.getAction()==MotionEvent.ACTION_UP){
            PlayerSprite.moveLeft=false;
            PlayerSprite.moveRight=false;
        }
        return true;
    }


}
