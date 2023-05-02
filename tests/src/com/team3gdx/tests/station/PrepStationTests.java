package com.team3gdx.tests.station;

import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.station.CookingStation;
import com.team3gdx.game.station.PrepStation;
import org.junit.Test;
import com.team3gdx.tests.GdxTestRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class PrepStationTests {
    @Test
    public void slotsToRecipe(){
        Ingredients i = new Ingredients();
        PrepStation prep = new PrepStation(null);

        prep.place(Ingredients.lettuceChopped);
        prep.place(Ingredients.tomatoChopped);
        prep.place(Ingredients.onionChopped);
        assertTrue(prep.slotsToRecipe());

        prep.place(Ingredients.flour);
        assertFalse(prep.slotsToRecipe());

        prep = null;
        i = null;
    }

    @Test
    public void lockCook(){
        PrepStation cooking = new PrepStation(null);
        assertTrue(cooking.lockCook());

        cooking = null;
    }
}
