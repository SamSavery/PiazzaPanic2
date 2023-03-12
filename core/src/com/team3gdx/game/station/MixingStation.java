package com.team3gdx.game.station;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.screen.GameScreen;

public class MixingStation extends Station {
    private final static Ingredient[] ALLOWED_INGREDIENTS = {Ingredients.flour};

    public float currentMixTime;



    /**
     * @param pos                   The (x, y) coordinates of the station.
     * @subparam numberOfSlots      The number of slots on the station.
     * @subparam infinite           Indicates if the station has an unlimited supply of
     *                              ingredients.
     * @subparam allowedIngredients A list of allowed ingredients in the station's
     *                              slots.
     * @subparam soundPath
     */
    public MixingStation(Vector2 pos) {
        super(pos, 1, false, ALLOWED_INGREDIENTS, "audio/soundFX/chopping.mp3");
    }


    /**
     * Used to mix if cook interacts with stations
     *
     * @param batch
     * @param dT
     */
    public void interact(SpriteBatch batch, float dT) {
        currentMixTime += dT;
        if (slots.peek().mix(batch , currentMixTime)) { // resets mix time counter after its mixed.
            currentMixTime = 0;
        }
    }

    /**
     * Lock interacting cook at station.
     *
     * @return A boolean indicating if the cook was successfully locked.
     */
    public boolean lockCook() {
        if (!slots.isEmpty()) {
            if (lockedCook == null) {
                GameScreen.cook.locked = true;
                lockedCook = GameScreen.cook;
            } else {
                lockedCook.locked = true;
            }
            slots.peek().mixing = true;

            return true;
        }
        if (lockedCook != null) {
            lockedCook.locked = false;
            lockedCook = null;
            currentMixTime = 0;
        }

        return false;
    }
}
