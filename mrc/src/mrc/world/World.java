package mrc.world;

import java.util.HashMap;

public class World {

	private static HashMap<String, CreatureRarity> creatureRarities = new HashMap<String, CreatureRarity>();
	
	public static void shiftPopulation(String id, int populationShift, int trend){
		
		CreatureRarity creatureRarity;
		
		if (creatureRarities.containsKey(id)){
			
			creatureRarity = new CreatureRarity(id);
			
			creatureRarities.put(id, creatureRarity);
			
		} else {
			
			creatureRarity = creatureRarities.get(id);
			
		}
		
		creatureRarity.setPopulation(creatureRarity.getPopulation() + populationShift);
		
		creatureRarity.setTrend(creatureRarity.getTrend() + trend);
		
	}
	
}
