package mrc.world;

import mrc.ecosystem.Species;

public class RegisteredSpecies implements Comparable<RegisteredSpecies>{
	
	private int order;
	
	private String id;
	
	private int worldPopulation;
	
	private int rarity;
	
	public RegisteredSpecies(Species species){
				
		this.order = species.getOrder();
		
		this.id = species.getId();
		
	}

	public int getOrder() {
		
		return order;
		
	}

	public void setOrder(int order) {
		
		this.order = order;
		
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
	public int compareTo(RegisteredSpecies other) {

		if (this.order == ((RegisteredSpecies)other).getOrder()){
			
			return this.worldPopulation - ((RegisteredSpecies)other).getWorldPopulation();
			
		} else {
			
			return this.order - ((RegisteredSpecies)other).getOrder();
			
		}
		
	}		
	
}
