package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class for the T-Rex Dino
 */
public class Trex extends Dinosaur {

	GameMap map;
	private CarnivoreFood carnFood;
	/**
	 * Constructor for Trex
	 * @param name			The name of the T-Rex
	 * @param displayChar	The character that represents it on the map
	 * @param hitPoints		The amount of health it has
	 */
	public Trex(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, Egg.DinosaurType.T_REX);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Main constructor for Trex
	 * @param name
	 * sets hunger HP and Behaviour
	 */
	public Trex(String name) {
		super(name, 'R', 100, Egg.DinosaurType.T_REX);
		hunger = 20;
		hitPoints = 50;
		this.behaviour = new HuntBehaviour("Proto", "Veloc", carnFood, new Corpse("Dead"));
		this.addSkill(SkillCollection.LAND_WALK);
	}
	
	
}

