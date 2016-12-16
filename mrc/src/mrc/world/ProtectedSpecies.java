package mrc.world;

import mrc.ecosystem.Species;

public class ProtectedSpecies implements Comparable<ProtectedSpecies>{
	
	private int level;
	
	private String id;
	
	private int worldPopulation;
	
	private int rarity;
	
	public ProtectedSpecies(Species species){
				
		this.level = species.getLevel();
		
		this.id = species.getId();
		
	}

	public int getLevel() {
		
		return level;
		
	}

	public void setLevel(int level) {
		
		this.level = level;
		
	}

	public String getId() {
		
		return id;
		
	}

	public void setId(String id) {
		
		this.id = id;
		
	}

	public int getWorldPopulation() {
		
		return worldPopulation;
		
	}

	public void setWorldPopulation(int worldPopulation) {
		
		this.worldPopulation = worldPopulation;
		
	}	

	public int getRarity() {
		
		return rarity;
		
	}

	public void setRarity(int rarity) {
		
		this.rarity = rarity;
		
	}

	@Override
	public int compareTo(ProtectedSpecies other) {

		if (this.level == ((ProtectedSpecies)other).getLevel()){
			
			return this.worldPopulation - ((ProtectedSpecies)other).getWorldPopulation();
			
		} else {
			
			return this.level - ((ProtectedSpecies)other).getLevel();
			
		}
		
	}		
	
}
