package mrc.world;

import java.util.HashMap;

import mrc.ecosystem.FantasyPark;
import mrc.json.FactoryJSON;
import mrc.json.MrcResponseJSON;
import mrc.player.Order;
import mrc.player.Player;

public class World {

	public static HashMap<String, Player> players = new HashMap<String, Player>();
	
	public static MrcResponseJSON executeOrder(Order order){
		
		Player player = players.get(order.getPlayerName());
		
		FantasyPark fantasyPark = player.getFantasyPark();
		
		//...
		
		MrcResponseJSON response = FactoryJSON.wrapResponse(player);
		
		return response;
		
	}
	
	
	public static void main(String[] args) {

		

	}

}
