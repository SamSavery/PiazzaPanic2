package com.team3gdx.game.food;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class OrderCard {

    public String name;
    private String textureFilePath;
    private Texture texture;
    private long creationTime;
    private float maxWaitTime;
    private HashMap<String, Integer> dishWaitTimes = new HashMap<>(){{
        put("salad", 50000);
        put("burger", 75000);
        put("pizza", 100000);
        put("jacket_potato", 40000);
    }};
    private float orderCardWidth;
    private float orderCardHeight;
    private float orderCardPosX;
    private float orderCardPosY;

    public OrderCard(String name){
        this.name = name;
        this.textureFilePath = "uielements/queued" + name.substring(0,1).toUpperCase() + name.substring(1) + ".png";
        this.texture = new Texture(Gdx.files.internal(textureFilePath));
        this.creationTime = System.currentTimeMillis();
        this.maxWaitTime = dishWaitTimes.get(name);
        this.orderCardHeight = 115;
        this.orderCardWidth = 115;
        this.orderCardPosX = 50;
        this.orderCardPosY = 50;
    }

    public String getName(){
        return this.name;
    }
    public Texture getTexture(){
        return this.texture;
    }
    public float getX(){
        return this.orderCardPosX;
    }
    public float getY(){
        return this.orderCardPosY;
    }
    public float getHeight(){
        return this.orderCardHeight;
    }
    public float getWidth(){
        return this.orderCardWidth;
    }

    public Boolean hasExpired(){
        if (System.currentTimeMillis()- creationTime >= maxWaitTime){
            return true;
        }
        return false;
    }
}
