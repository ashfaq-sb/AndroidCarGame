package com.example.ash.cargame;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by Belal Khan on 11/5/2014.
 */
public class GameThread extends Thread{
    private GamePlay game;
    private boolean running=false;

    public void startGame(boolean start){
        running = start;
    }

    public GameThread(GamePlay game){
        this.game = game;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        // Main Game Loop

        long lastTime = System.nanoTime();
        double amountOFTicks = 60.0; // seconds
        double ns = 100000000 / amountOFTicks; // convert to nano seconds
        double delta = 0;
        long timer = System.currentTimeMillis();
        @SuppressWarnings("unused")
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                game.update();
                delta--;
            }
            if (running) {
                Canvas c = null;
                try {
                    c = game.getHolder().lockCanvas();
                    synchronized (game.getHolder()) {
                        game.onDraw(c);
                    }
                } finally {
                    if (c != null) {
                        game.getHolder().unlockCanvasAndPost(c);
                    }

                    frames++;

                    if (System.currentTimeMillis() - timer > 1000) {


                        timer += 2000;
                        System.out.println("FramesPerSecond: " + frames);
                        frames = 0;

                    }

                }

            }

        }
    }

}
