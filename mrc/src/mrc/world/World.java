package mrc.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mrc.ecosystem.FantasyPark;
import mrc.json.FactoryJSON;
import mrc.json.MrcResponseJSON;
import mrc.player.Human;
import mrc.player.Order;
import mrc.player.Player;
import mrc.player.Robot;

public class World {

	public static HashMap<String, Player> players = new HashMap<String, Player>();
	
	public static void create(List<Robot> robots, List<Human> humans){
		
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
		
		ArrayList<Robot> robots = new ArrayList<Robot>();
		
		ArrayList<Human> humans = new ArrayList<Human>();
		
		for (int i=1; i<=10; i++){
			
			Robot robot = new Robot("R" + i);
			
			robots.add(robot);
			
		}

		World.create(robots, humans);

	}

}
