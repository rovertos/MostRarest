package mrc.world;

import java.util.ArrayList;
import java.util.Collections;

import mrc.config.GlobalConstants;

public class World {

	private static ArrayList<WorldCreature> worldCreatures = new ArrayList<WorldCreature>();
	
	private static ArrayList<CreatureRarity> creatureRarities = new ArrayList<CreatureRarity>();
	
	public static void shiftPopulation(String id, int populationShift, int rarityBonus, int trend){
		
		WorldCreature WorldCreature = new WorldCreature(id);
		
		WorldCreature.setRarbonus(rarityBonus);
		
		if (!worldCreatures.contains(id)){
			
			worldCreatures.add(WorldCreature);
			
		} else {
			
			WorldCreature = worldCreatures.get(worldCreatures.indexOf(WorldCreature));
			
		}
		
		WorldCreature.setPopulation(WorldCreature.getPopulation() + populationShift);
		
		WorldCreature.setTrend(WorldCreature.getTrend() + trend);
		
	}
	
	public static void calculateRarities(){
		
		creatureRarities.clear();
		
		Collections.sort(worldCreatures);
		
		int pTemp = 0;
		
		for (int i=0; i<worldCreatures.size(); i++){
			
			WorldCreature worldCreature = worldCreatures.get(i);
			
			CreatureRarity creatureRarity;
			
			if (creatureRarities.isEmpty() || worldCreature.getPopulation() > pTemp){
				
				creatureRarity = new CreatureRarity();
				
				pTemp = worldCreature.getPopulation();
				
			} else {
				
				creatureRarity = creatureRarities.get(creatureRarities.size()-1);
				
			}
			
			creatureRarity.addWorldcreature(worldCreature);			
			
		}		
		
		int worldSizeBonus = worldCreatures.size() / GlobalConstants.WORLD_DIVIDER;		
		
		for (int i=0; i<creatureRarities.size(); i++){
			
			CreatureRarity creatureRarity = creatureRarities.get(i);
			
			if (i == 0){
				
				creatureRarity.setValue(GlobalConstants.TOP_1);
				
			} else if (i < 2){
				
				creatureRarity.setValue(GlobalConstants.TOP_3);
				
			} else if (worldSizeBonus > 0 && i < creatureRarities.size() / 2){
				
				creatureRarity.setValue(GlobalConstants.TOP_HALF);
				
			}
			
		}
		
		for (int i=0; i<creatureRarities.size(); i++){
			
			CreatureRarity creatureRarity = creatureRarities.get(i);
					
			for (WorldCreature worldCreature: creatureRarity.getWorldcreatures()){
				
				int rarity = creatureRarity.getValue() + worldCreature.getRarbonus() + worldSizeBonus;
				
				worldCreature.setRarity(rarity);
				
			}
			
		}
		
	}
	
}
