package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the dinosaur park game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Shop(), new Water(), new Reed());


		List<String> southMap = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....###.###....................................................................",
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

		List<String> northMap = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".......+++......................................................................",
		".....++++.......................................................................",
		".......++...............................................................+++.....",
		"........++++......................~~~~~~~~~~~~~~..........................++....",
		"..............................~~~~~~~~~~~~~~~~~~~~.......................++.....",
		"..........................~~~~~~~~~~~~~~~~~~~~~~~~~~~.....................++....",
		".........................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...................+++....",
		"........................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.........................",
		"........................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.........................",
		"........................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.........................",
		"........................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.........................",
		"........................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.........................",
		".........................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~..........................",
		"..........................~~~~~~~~~~~~~~~~~~~~~~~~~~~...................++......",
		"...........................~~~~~~~~~~~~~~~~~~~~~~~~~.....................+++....",
		".............................~~~~~~~~~~~~~~~~~~~~~.....................++++.....",
		"...............................~~~~~~~~~~~~~~~~~.........................+++....",
		"........++++.......########......~~~~~~~~~~~~~..........................++++....",
		"......+++++++++....#------#..........~~~~~............................++++......",
		"..........++.......#-------............................................+++......",
		"...........+++.....#####---.....................................................",
		"..........++....................................................................",
		"................................................................................");

		//Change southMap to northMap to swap maps on line below
		GameMap southGameMap = new GameMap(groundFactory, southMap);
		GameMap northGameMap = new GameMap(groundFactory, northMap);
		world.addGameMap(southGameMap);
		world.addGameMap(northGameMap);
		
		//Loop to add exits to the north of the south map, and the south of the north map
		for (int i = 0; i < northGameMap.getXRange().max(); i++) {
			southGameMap.at(i, southGameMap.getYRange().min()).addExit(new Exit("North to new map", northGameMap.at(i, northGameMap.getYRange().max()), "8"));
			northGameMap.at(i, northGameMap.getYRange().max()).addExit(new Exit("South to new map", southGameMap.at(i, southGameMap.getYRange().min()), "2"));
		}

		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, southGameMap.at(8, 4));

		//Populate South Map
		southGameMap.at(8, 6).addActor(new Protoceratops("Protoceratops"));
		southGameMap.at(10, 12).addActor(new Protoceratops("Protoceratops"));
		southGameMap.at(60, 11).addActor(new Velociraptor("Velociraptor"));
		//southGameMap.at(63, 12).addActor(new Trex("Trex"));
		southGameMap.at(62, 11).addItem(new Corpse("Dead"));
	
		//Populate North Map
		northGameMap.at(30, 10).addActor(new Fish());
		northGameMap.at(31, 11).addActor(new Plesioaur("Plesioaur"));
		northGameMap.at(60, 12).addItem(new CarnivoreFood());
		northGameMap.at(10, 12).addActor(new Protoceratops("Protoceratops"));

		world.run();
	}
}
