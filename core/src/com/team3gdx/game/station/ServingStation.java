package com.team3gdx.game.station;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.entity.Customer;
import com.team3gdx.game.food.*;
import com.team3gdx.game.screen.GameScreen;

public class ServingStation extends Station {
	public String name = "ServingStation";
	String[] possibleOrders = new String[] { "burger", "salad" };
	/**
	 * Configure allowed ingredient to be those on the menu.
	 */
	static Ingredient[] allowedIngredients = new Ingredient[Menu.RECIPES.size()+1];
	static {
		allowedIngredients[0]= Ingredients.cooked_pizza;
		int i = 1;
		for (Recipe recipe : Menu.RECIPES.values()) {
			allowedIngredients[i] = new Ingredient(recipe);
			i++;
		}
	};


	public ServingStation(Vector2 pos) {
		super(pos, 1, false, allowedIngredients, "audio/soundFX/money-collect.mp3");
	}

	/**
	 * Check if there is a customer waiting, add their order to the orderCards and check if the
	 * serving station contains any active orders.
	 */
	public void takeCustomerOrder() {
		Customer waitingCustomer = GameScreen.cc.isCustomerAtPos(new Vector2(pos.x - 1, pos.y));
		if (waitingCustomer != null && waitingCustomer.locked) {
			if (GameScreen.orderCards.isEmpty() || GameScreen.orderCards.size() < GameScreen.NUMBER_OF_WAVES) {
				GameScreen.orderCards.add(new OrderCard(possibleOrders[new Random().nextInt(possibleOrders.length)]));
				waitingCustomer.arrived();
				GameScreen.cc.delCustomer(waitingCustomer);
			}
		}
	}
	public void serveOrder(){
		if (!slots.empty()) {
			//Stupid more verbose for-loop to prevent concurrentModification errors.
			for (Iterator<OrderCard> iterator = GameScreen.orderCards.iterator(); iterator.hasNext();) {
				OrderCard order = iterator.next();
				if (!slots.isEmpty() && slots.peek().equals(Menu.RECIPES.get(order.getName().substring(0,1).toUpperCase() + order.getName().substring(1)))){
					iterator.remove();
					slots.pop();
				}
			}
			GameScreen.currentWave++;
		}
	}

}
