package mrc.ecosystem;

import java.util.List;

import mrc.world.World;

public class Species extends Consumable {

	private List<Consumable> diet;
	
	private String category;
	
	private int index;
	
	private int trend;
	
	private float gmult;
	
	private int rarbonus;
	
	public Species(String category, int index, int gbuf, float gmult, int rarbonus){
		
		this.category = category;
		
		this.index = index;
		
		this.setGbuf(gbuf);
		
		this.gmult = gmult;
		
		this.rarbonus = rarbonus;
		
	}
	
	@Override
	public String getId() {

		return this.category + this.index;
		
	}
	
	public List<Consumable> getDiet() {
		
		return diet;
		
	}

	public void setDiet(List<Consumable> diet) {
		
		this.diet = diet;
		
	}
	
	public void increase(float growthOverload){
		
		this.trend = 1;
		
		this.setAvailable(this.getAvailable() + 1);
		
		World.shiftPopulation(this.getId(), 1, this.getRarbonus(), trend);
		
		this.setGrowth(growthOverload);
		
	}
	
	public void decrease(float growthOverload){
		
		this.trend = -1;
		
		this.setAvailable(this.getAvailable() - 1);
		
		World.shiftPopulation(this.getId(), -1, this.getRarbonus(), trend);
		
		this.setGrowth(-growthOverload);
		
	}
	
	public int getTrend() {
		
		return trend;
		
	}

	public int getRarbonus() {
		
		return rarbonus;
		
	}
	
	public float getGmult() {
		
		return gmult;
		
	}

	public void setGmult(float gmult) {
		
		this.gmult = gmult;
		
	}	

	public int getAvailableFoods(){
		
		int foodSources = 0;
		
		for (Consumable consumable: this.diet){
			
			if (consumable.getAvailable() > 0)
				
				foodSources++;
			
		}
		
		return foodSources;
		
	}
	
	public float getFoodPlethora(){
		
		float foodPlethora = 0;
		
		for (Consumable prey: this.getDiet()){
			
			if (prey.getAvailable() > 0){
				
				foodPlethora += prey.getAvailable() / prey.getActiveConsumers();
				
			}
			
		}
		
		return foodPlethora;
		
	}
	
}
