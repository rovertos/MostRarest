package mrc.ecosystem;

import java.util.List;

public abstract class Consumable {
	
	private List<Species> consumers;
	
	// available: 0 - 10
	private int available = 0;
	
	// growth>=gbuf => available+1, growth<=-gbuf => available-1;
	private float growth = 0;
	
	private float gbuf = 0;
	
	private float gmult = 1;
	
	private int trend;
	
	public abstract String getId();
	
	public List<Species> getConsumers() {
		
		return consumers;
		
	}

	public void setConsumers(List<Species> consumers) {
		
		this.consumers = consumers;
		
	}
	
	public int getAvailable(){
		
		return this.available;
		
	}
	
	public void setAvailable(int available){
		
		this.available = available;
		
	}
	
	public float getGrowth() {
		
		return growth;
		
	}
	
	public void setGrowth(float growth) {
		
		this.growth = growth;
		
	}	
	
	public void addGrowth(float growthShift){
		
		growth += growthShift;
		
	}
	
	public void increase(float growthOverload){
		
		this.trend = 1;
		
		this.setGrowth(growthOverload);
		
	}
	
	public void decrease(float growthOverload){
		
		this.trend = -1;
		
		this.setGrowth(-growthOverload);
		
	}	
	
	public int getTrend() {
		
		return trend;
		
	}

	public float getGbuf() {
		
		return gbuf;
		
	}

	public void setGbuf(float gbuf) {
		
		this.gbuf = gbuf;
		
	}	
	
	public float getGmult() {
		
		return gmult;
		
	}

	public void setGmult(float gmult) {
		
		this.gmult = gmult;
		
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

	public int getActiveConsumers(){
		
		int consumers = 0;
		
		for (Species species: this.consumers){
			
			if (species.getAvailable() > 0)
				
				consumers++;
			
		}
		
		return consumers;
		
	}	
	
}
