package com.team3gdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Customer {
    int targetsquare;
    public int posx;
    public int posy;
    Texture textf;
    Texture textb;
    Texture textr;
    Texture textl;

    TextureRegion[][] custpartsf;
    TextureRegion[][] custpartsb;
    TextureRegion[][] custpartsr;
    TextureRegion[][] custpartsl;
    TextureRegion[][] currentcustparts;
    int startposx;
    int targetpixel;
    public boolean locked;
    public boolean readyfordeletion;

    public long arrivalTime;
    public static long maxWaitTime;

    /**
     * Constructor for customer class
     *
     * @param x      - x starting pixel coordinate
     * @param y      - y starting pixel coordinate
     * @param tg     - target y cell coordinate - not in pixels
     * @param custno - customer number - changes texture
     */
    public Customer(int x, int y, int tg, int custno) {
        targetsquare = tg;
        textf = new Texture("entities/cust" + custno + "f.png");
        textb = new Texture("entities/cust" + custno + "b.png");
        textr = new Texture("entities/cust" + custno + "r.png");
        textl = new Texture("entities/cust" + custno + "l.png");

        custpartsf = TextureRegion.split(textf, 32, 32);
        custpartsb = TextureRegion.split(textb, 32, 32);
        custpartsr = TextureRegion.split(textr, 32, 32);
        custpartsl = TextureRegion.split(textl, 32, 32);

        currentcustparts = custpartsb;

        posx = x * 64;
        posy = y * 64;
        startposx = posx;
        locked = false;
        maxWaitTime = 15000;
    }

    /**
     * Set arrival time as when customer reaches serving station
     */
    public void arrived() {
        arrivalTime = 18000 + System.currentTimeMillis();
    }

    /**
     * Checks if customer has been waiting too long, returns true if so.
     */
    public Boolean hasExpired() {
        System.out.println("Arrival time: " + arrivalTime);
        System.out.println("Waiting time: " + (System.currentTimeMillis() - arrivalTime));
        return System.currentTimeMillis() - arrivalTime >= maxWaitTime;
    }

    /**
     * Render top of customer
     *
     * @param b - spritebatch to render with
     */
    public void renderCustomersTop(Batch b) {
        b.draw(currentcustparts[0][0], posx, posy + 64, 64, 64);
    }

    /**
     * Render bottom of customer
     *
     * @param b - spritebatch to render with
     */
    public void renderCustomersBot(Batch b) {
        b.draw(currentcustparts[1][0], posx, posy, 64, 64);
    }

    /**
     * Set target square location
     *
     * @param tg - tile y coordinate of target square
     */
    public void setTargetsquare(int tg) {
        targetsquare = tg;
    }

    /**
     * Move towards customer target tile
     */
    public void stepTarget() {
        targetpixel = 32 + (targetsquare * 64);
        if (posy < targetpixel) {
            posy++;
        } else if (posy > targetpixel) {
            posy--;
            currentcustparts = custpartsf;
        }
        if (posy == targetpixel) {
            if (targetpixel > 0) {
                posx = startposx + 64;
                locked = true;
                currentcustparts = custpartsr;
            } else {
                readyfordeletion = true;
            }
        }
    }
}
