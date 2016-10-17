package com.example.ash.cargame;

import android.graphics.Canvas;

import java.util.LinkedList;


public class GameObjectHandler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick(){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.update();
        }

    }
    public void render(Canvas canvas){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.onDraw(canvas);
        }
    }
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public int countOfObject(ID id){
        int count=0;
        for (int i = 0; i < object.size(); i++) {

            GameObject tempObject = object.get(i);
            if(tempObject.getId() == id){
                count++;
            }
        }
        return count;
    }

}
