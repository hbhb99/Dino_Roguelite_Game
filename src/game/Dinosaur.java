package game;

import edu.monash.fit2099.engine.Action;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.Egg;
import game.Egg.DinosaurType;

/**
 * The base class for Dinosaurs
 *
 */

public class Dinosaur extends Actor {

	
	protected DinosaurType dinoType;
	/**
	 * Hunger value for dinos
	 */
	public int hunger = 0;

	/**
	 * What the dino is worth if it is eaten
	 */
	public int foodValue = 0;

	/**
	 * used to initialize a specific behaviour
	 */
	public Behaviour behaviour;
	
	public Dinosaur(String name, char displayChar, int hitPoints, DinosaurType initDinoType) {
		super(name, displayChar, hitPoints);
		dinoType = initDinoType;
	}
	/**
	 * Method for decrementing hunger and returning an action for each dino 
	 * @return an action for the Dino to do that turn
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		hunger -=1;
		 if (hunger <= 0) {
			return new dieAction();
		}
		 /**
		  * Random chance to 'breed' an egg if A dino is healthy
		  */
		 if (hunger > 20) {
			 double random = Math.random();
				if (random < 0.001) {
					map.locationOf(this).addItem(new Egg(dinoType, true));
				}

		}
		 Action action = behaviour.getAction(this, map);
			//Action action = factory.getAction(this, map);
			if(action != null)
				return action;
				System.out.print("actioncalled");
			
			return new DoNothingAction();
	}

	/*
	 * getters and setters for hunger and foodValue
	 */
	void setHunger(int changeHunger) {
		hunger = changeHunger;
	}
	
	int getHunger() {
		 return hunger;
	}
	int getFoodValue() {
		return foodValue;
	}
	public void increaseHunger(int hungerPoints) {
		hunger += hungerPoints;
	}
}
