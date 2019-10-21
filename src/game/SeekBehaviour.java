package game;



import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;


public class SeekBehaviour implements Behaviour {
	private Random random = new Random();
	private Location target = null;
	private String food;
	private String food2;
	private Item foodItem;
	private Item foodItem2;
	private Item foodMisc;
	private Boolean isGround = false;
	
	
	public SeekBehaviour(String foodSource, Item itemFood, String secondaryfoodSource,Item secondaryitemFood,Item miscFood) {
		//
		//FIND CLOSEST TREE
		//		
		food = foodSource;
		food2 = secondaryfoodSource;
		foodItem = itemFood;
		foodItem2 = secondaryitemFood;
		foodMisc = miscFood;
		
		//this.target = subject;
	}

	public SeekBehaviour(String foodSource, HerbivoreFood herbivoreFood) {
		food = foodSource;
		foodItem = herbivoreFood;
	}
	public SeekBehaviour(String foodSource, CarnivoreFood herbivoreFood) {
		food = foodSource;
		foodItem = herbivoreFood;
	}


	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		if(!map.contains(actor))
		return null;
		
		Location here = map.locationOf(actor);
		int minX;
		int minY;
		int maxX;
		int maxY;
		
		//x min
		if (here.x()-5 <= map.getXRange().min()) {minX =  map.getXRange().min();}
		else {minX = here.x()-5;}
		//y min
		if (here.y()-5 <= map.getYRange().min()) {minY =  map.getYRange().min();}
		else {minY = here.y()-5;}
		//xMax
		if (here.x()-5 <= map.getXRange().max()) {maxX = map.getXRange().max();}
		else {maxX = here.x()+5;}
		//maxY
		if (here.x()-5 <= map.getYRange().max()) {maxY = map.getYRange().max();}
		else {maxY = here.y()+5;}
		outerloop:

			for (int i = minX; i < maxX; i++) {
				for (int k = minY; k < maxY; k++) {
					if (food !=null && map.at(i, k).getGround().toString().contains(food)){
						target = map.at(i,k);
						isGround = true;
						break outerloop;
					}
					else if (food2!=null && map.at(i, k).getGround().toString().contains(food2)) {
						target = map.at(i,k);
						isGround = true;
						break outerloop;

					}
					else if (foodItem!=null&& map.at(i, k).getItems().contains(foodItem)){
						target = map.at(i,k);
						isGround = false;
						break outerloop;
					}
					else if(foodItem2!=null&& map.at(i, k).getItems().contains(foodItem2))
					{
						target = map.at(i,k);
						isGround = false;
						break outerloop;
					}
					else if(foodMisc!=null&& map.at(i, k).getItems().contains(foodMisc))
					{
						target = map.at(i,k);
						isGround = false;
						break outerloop;
					}
					
				}
	
			}		
		
		int currentDistance = distance(here, target);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, target);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}
		if (currentDistance == 0) 
		{
			if (isGround == true) {
				here.setGround(new Dirt());
			}
			else if (isGround== false) {
				if (here.getItems().contains(foodItem)) {
					here.getItems().remove(foodItem);
				}
				if (here.getItems().contains(foodItem2)) {
					here.getItems().remove(foodItem2);
				}
				if (here.getItems().contains(foodMisc)) {
					here.getItems().remove(foodMisc);
				}
				
				
			}
		
				
		}
		//Wander Behaviour
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
	
		return null;
	}
	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}