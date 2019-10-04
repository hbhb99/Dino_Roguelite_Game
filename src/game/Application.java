package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the dinosaur park game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Shop());
		
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#-----#....................................................................",
		".....#-----#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++.......................................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(8, 4));
		
		// Place a pair of protoceratops in the middle of the map
		Protoceratops proto1 = new Protoceratops("Protoceratops");
		gameMap.at(40, 10).addActor(proto1);
		gameMap.at(32, 12).addActor(new Protoceratops("Protoceratops"));
		
		//adds Velociraptors 
		Velociraptor veloc1 = new Velociraptor("Velociraptor");
<<<<<<< HEAD
		veloc1.actionFactories.add(new HuntBehaviour());
		veloc1.actionFactories.add(new WanderBehaviour());
		gameMap.at(30, 12).addActor(veloc1);
		//gameMap.at(10, 15).addActor(new Velociraptor("Velociraptor"));	
		
		//adds an egg
		gameMap.at(8, 14).addItem(new Egg(DinosaurType.PROTOCERATOPS));
		
=======
		veloc1.actionFactories.add(new HuntBehaviour(proto1));
		veloc1.actionFactories.add(new FollowBehaviour(proto1));
		gameMap.at(25, 20).addActor(veloc1);
		//gameMap.at(10, 15).addActor(new Velociraptor("Velociraptor"));
>>>>>>> 9c0b24fb97dcea169c814aa97a6dba0c48f4f37d
		
		world.run();
	}
}
