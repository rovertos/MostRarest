package mrc.ecosystem;

import java.util.List;

public class Species extends Consumable {

	private List<Consumable> diet;
	
	private String category;
	
	private int index;
	
	private int trend;
	
	public Species(String category, int index, int gbuf){
		
		this.category = category;
		
		this.index = index;
		
		this.setGbuf(gbuf);
		
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
		
	}
	
	public void decrease(float growthOverload){
		
		this.trend = -1;
		
		this.setGrowth(-growthOverload);
		
	}

	public int getTrend() {
		
		return trend;
		
	}

	public void setTrend(int trend) {
		
		this.trend = trend;
		
	}

	public int getAvailableFoods(){
		
		int foodSources = 0;
		
		for (Consumable consumable: this.diet){
			
			if (consumable.getAvailable() > 0)
				
				foodSources++;
			
		}
		
		return foodSources;
		
	}
	
	public float getPredatorBurden(){
		
		float predatorBurden = 0;
		
		for (Species predator: this.getConsumers()){
			
			if (predator.getAvailable() > 0){
				
				predatorBurden += predator.getAvailable() / predator.getAvailableFoods();
				
			}
			
		}
		
		return predatorBurden;		
		
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
