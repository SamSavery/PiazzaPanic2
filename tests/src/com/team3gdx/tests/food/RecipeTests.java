package com.team3gdx.tests.food;

import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Recipe;
import org.junit.Test;
import com.team3gdx.tests.GdxTestRunner;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

@RunWith(GdxTestRunner.class)
public class RecipeTests {
    @Test
    public void matches(){
        final Map<Ingredient, String> SALAD_STEPS = new HashMap<Ingredient, String>();
        Recipe salad = new Recipe("", null, SALAD_STEPS, "serve together", "salad", false, null, 32, 32, 0);


        //salad.matches();

        salad = null;
    }

    @Test
    public void contains(){}
}
