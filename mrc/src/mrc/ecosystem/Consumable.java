package mrc.ecosystem;

import java.util.List;

public abstract class Consumable {
	
	private List<Species> consumers;
	
	// available: 0 - 10
	private int available = 0;
	
	// growth>=gbuf => available+1, growth<=-gbuf => available-1;
	private float growth = 0;
	
	private float gbuf = 0;
	
	public abstract String getId();
	
	public List<Species> getConsumers() {
		
		return consumers;//
		
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
	
	public float getGbuf() {
		
		return gbuf;
		
	}

	public void setGbuf(int gbuf) {
		
		this.gbuf = gbuf;
		
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
