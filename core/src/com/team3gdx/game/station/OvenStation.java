package com.team3gdx.game.station;

import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;

public class OvenStation extends CookingStation {
    private final static Ingredient[] ALLOWED_INGREDIENTS = {Ingredients.cooked_pizza, Ingredients.burnt_pizza, Ingredients.raw_pizza, Ingredients.potato, Ingredients.potato_cooked, Ingredients.potato_burned};

    public OvenStation(Vector2 pos) {
        super(pos, 1, ALLOWED_INGREDIENTS, "particles/smokes.party", "audio/soundFX/frying.mp3");
    }

    @Override
    public boolean place(Ingredient ingredient) {
        if (super.place(ingredient)) {
            ingredient.flipped = true;
            return true;
        }

        return false;
    }


}