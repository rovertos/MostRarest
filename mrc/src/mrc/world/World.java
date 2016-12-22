package mrc.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mrc.config.Global;
import mrc.config.ActiveConfig;
import mrc.ecosystem.FantasyPark;
import mrc.json.FactoryJSON;
import mrc.json.MrcResponseJSON;
import mrc.player.Human;
import mrc.player.Order;
import mrc.player.Player;
import mrc.player.Robot;
import mrc.util.Logger;

public class World {

	public static HashMap<String, Player> players = new HashMap<String, Player>();
	
	public static TheWild theWild;
	
	public static void create(List<Robot> robots, List<Human> humans){
				
		theWild = new TheWild();
		
		theWild.genesis();
		
		Logger logger = new Logger();
		
		logger.setTabLength(30);
		
		logger.print(theWild);
		
		for (int i=0; i<Global.RUN_TEST_STEPS; i++){
			
			theWild.executeStep();
			
			logger.print(theWild);
			
		}
		
		for (Robot robot: robots){
			
			
			
		}
		
		for (Human human: humans){
			
			
			
		}		
		
	}
	
	public static MrcResponseJSON executeOrder(Order order){
		
		Player player = players.get(order.getPlayerName());
		
		FantasyPark fantasyPark = player.getFantasyPark();
		
		//...
		
		MrcResponseJSON response = FactoryJSON.wrapResponse(player);
		
		return response;
		
	}
	
	
	public static void main(String[] args) {
		
		ActiveConfig.initialize();
		
		ArrayList<Robot> robots = new ArrayList<Robot>();
		
		ArrayList<Human> humans = new ArrayList<Human>();
		
		for (int i=1; i<=Global.NUMBER_OF_ROBOTS; i++){
			
			Robot robot = new Robot("R" + i);
			
			robots.add(robot);
			
		}

		World.create(robots, humans);

	}

}
