package mrc.ecosystem;

import mrc.config.Global;
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
		
		System.out.println(this.getId() + " executeStep with status = " + this.status);		
	
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
			
			buf.append(this.getId() + ": " + oldStatus + "[" + Global.formatter.format(growthFactor) + "] => " + Global.formatter.format(growthThisStep) + " + " + Global.formatter.format(accumulatedGrowth) + " => " + this.status + ". ");
		
		}

	}
	
	public float getGrowthFactor(){
		
		System.out.println(this.getId() + " CALCULATING GROWTH FACTOR: ");
		
		float growthFactor = 0;
		
		for (Population predator: this.area.getPredators(this)){
			
			growthFactor -= this.giveDueShare(predator);
			
		}
		
		for (Countable prey: this.area.getDiet(this)){
			
			growthFactor += prey.giveDueShare(this);
			
		}
		
		return growthFactor;
				
	}
	
	public float giveDueShare(Countable askingPredator){
		
		//  0 <= giveDueShare <= 1
		System.out.println("...");
		System.out.println(askingPredator.getId() + " asks for due share from " + this.getId());
		
		//float totalPredatorPop = 0;
		
		float otherPredatorShare = 0;
		
		for (Population predator: this.area.getPredators(this)){
			
			//totalPredatorPop += predator.getStatus();
			
			if (!predator.getId().equals(askingPredator.getId()))
			
				otherPredatorShare += predator.giveDueShare(this);
			
		}
		
		//float dueShare = 1 - otherPredatorShare / totalPredatorPop;
		//float dueShare = (float)this.status / ((float)this.status + otherPredatorShare);
		float dueShare = (float)askingPredator.status * ((float)this.status / ((float)askingPredator.status + otherPredatorShare));
		
		
		System.out.println(this.getId() + " gives due share " + dueShare + " to askingPredator " + askingPredator.getId());
		System.out.println("...");
		
		return dueShare;
		
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
