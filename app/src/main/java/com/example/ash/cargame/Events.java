package com.example.ash.cargame;

import android.view.MotionEvent;
import android.view.View;

public class Events implements View.OnTouchListener {

    public GameObjectHandler handler;
    private GamePlay game;
    public Events(GameObjectHandler handler, GamePlay game) {
        super();
        this.handler = handler;
        this.game =game;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        for (int i=0; i<handler.object.size();i++){


        }


        return false;
    }
}
