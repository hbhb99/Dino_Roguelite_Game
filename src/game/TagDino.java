package game;

import edu.monash.fit2099.engine.*;

/**
 * Action to allow player to sell live dinosaurs
 */

public class TagDino extends Action {

	public TagDino() {
		
	}

	@Override
	public String execute(Actor player, GameMap map) {
		int dinoValue = 0;
		String dinoName = "";
		String executePrint = "";
		Actor dino = dinoFinder(player, map);
		
		//Checks what type of dino is being tagged
		if (dino.toString().equals("Protoceratops")){
			dinoValue = 100;
			dinoName = "Protoceratops";
		}
		else if (dino.toString().equals("Velociraptor")){
			dinoValue = 500;
			dinoName = "Velociraptor";
		}		
		else if (dino.toString().equals("Plesiosaur")){
			dinoValue = 5000;
			dinoName = "Plesiosaur";
		}		
		else if (dino.toString().equals("Pteranodon")){
			dinoValue = 5000;
			dinoName = "Pteranodon";
		}
		else if (dino.toString().equals("T-Rex")){
			dinoValue = 10000;
			dinoName = "T-Rex";
		}
		else {
			System.out.println("Cannot tag that actor.");
		}
		
		//Removes dino from game, and adds money to players wallet, and prints nice message
		map.removeActor(dino);
		((Player)player).addToWallet(dinoValue);
		removeTagInInventory(player);
		executePrint = dinoName + " tagged and sold for " + Player.moneyFormat(dinoValue);
		return executePrint;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Tag dinosaur";
	}
	
	
	/**
	 * Finds the dino around the player
	 * @param player 			The player
	 * @param map				The game map
	 * @return actorToReturn	The adjacent actor (dino) found in Player class
	 */
	private Actor dinoFinder(Actor player, GameMap map) {
		Actor actorToReturn;
		
		if (map.isAnActorAt(map.at(map.locationOf(player).x()+1, map.locationOf(player).y()))) {
			actorToReturn = map.getActorAt(map.at(map.locationOf(player).x()+1, map.locationOf(player).y()));
		}
		else if (map.isAnActorAt(map.at(map.locationOf(player).x()-1, map.locationOf(player).y()))) {
			actorToReturn = map.getActorAt(map.at(map.locationOf(player).x()-1, map.locationOf(player).y()));
		}
		else if(map.isAnActorAt(map.at(map.locationOf(player).x(), map.locationOf(player).y()+1))) {
			actorToReturn = map.getActorAt(map.at(map.locationOf(player).x(), map.locationOf(player).y()+1));
		}
//		else if(map.isAnActorAt(map.at(map.locationOf(player).x(), map.locationOf(player).y()-1))) {
//			actorToReturn = map.getActorAt(map.at(map.locationOf(player).x(), map.locationOf(player).y()-1));
//		}
		else {
			actorToReturn = map.getActorAt(map.at(map.locationOf(player).x(), map.locationOf(player).y()-1));
		}
		
		return actorToReturn;
	}
	
	/**
	 * Removes the DinoTag from the players inventory when a dino is tagged
	 * @param player	The player
	 */
	private void removeTagInInventory (Actor player) {
		int numInArray = 0;
		
		for (int i = 0; i < player.getInventory().size(); i++) {
			if (player.getInventory().get(i).hasSkill(SkillCollection.DINO_TAG)) {
				numInArray = i;
				break;
			}
		}
		
		player.removeItemFromInventory((player.getInventory().get(numInArray)));
	}

}
