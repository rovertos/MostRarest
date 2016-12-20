package mrc.ecosystem;

import mrc.geography.Area;
import mrc.geography.Location;
import mrc.world.FantasyWildlifeFund;

public class Population extends Countable {
	
	private Species species;
		
	public Population(Species species, int status, Area area, Location location, float growthThreshold, int carryingCapacityFactor){
		
		super(status, area, location, growthThreshold, carryingCapacityFactor);
		
		this.species = species;
		
		FantasyWildlifeFund.register(this);
		
	}

	public void executeStep(StringBuffer buf) {
		
		// TODO: STARVATION!

		float growthFactor = this.getGrowthFactor();
		
		int oldStatus = this.status;

		growthThisStep = growthFactor * status;

		accumulatedGrowth += growthThisStep;

		if (Math.abs(accumulatedGrowth) > growthThreshold) {

			if (accumulatedGrowth > 0 && status < 5) {

				status++;
				
				accumulatedGrowth = accumulatedGrowth - growthThreshold;
				
				FantasyWildlifeFund.register(this);

			} else if (accumulatedGrowth < 0 && status > 1) {
				
				status--;
				
				accumulatedGrowth = growthThreshold + accumulatedGrowth;
				
				FantasyWildlifeFund.register(this);
				
			}

		} 
		
		buf.append(this.getId() + ": " + oldStatus + "[" + growthFactor + "] => " + growthThisStep + " + " + accumulatedGrowth + " => " + this.status + ". ");

	}
	
	public float getGrowthFactor(){
		
		float growthFactor = 0;
		
		for (Countable predator: this.area.getPredators(this)){
			
			growthFactor -= predator.getDueShare(this);
			
		}
		
		for (Countable prey: this.area.getDiet(this)){
			
			growthFactor += prey.giveDueShare(this);
			
		}
		
		return growthFactor;
		
	}
	
	public float giveDueShare(Population population){
		
		int totalPredatorStatuses = 0;
		
		for (Countable predator: this.area.getPredators(this)){
			
			totalPredatorStatuses += predator.getStatus();
			
		}
		
		return (float)population.getStatus() * (float)this.status / (float)totalPredatorStatuses;
		
	}
	
	public float getDueShare(Countable countable){
		
		int totalPreyStatuses = 0;
		
		for (Countable prey: this.area.getDiet(this)){
			
			totalPreyStatuses += prey.getStatus();
			
		}
		
		return (float)countable.getStatus() * (float)this.status / (float)totalPreyStatuses;
		
	}
	
	public String getId(){
		
		return this.species.getId();
		
	}
	
	public Species getSpecies() {
		
		return species;
		
	}
	
	public int getAvailableFoods(){
		
		return area.getDiet(this).size();
		
	}	
	
}
