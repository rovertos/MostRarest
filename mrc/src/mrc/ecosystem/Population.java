package mrc.ecosystem;

import mrc.config.Global;
import mrc.geography.Area;
import mrc.geography.Location;
import mrc.world.FantasyWildlifeFund;

public class Population extends Countable {
	
	private Species species;
	
	private Diet diet;
		
	public Population(Species species, int status, Area area, Location location, float growthThreshold, int carryingCapacityFactor){
		
		super(status, area, location, growthThreshold, carryingCapacityFactor);
		
		this.species = species;
		
		FantasyWildlifeFund.register(this);
		
	}

	public void executeStep(StringBuffer buf) {
		
		// TODO: STARVATION!		

		float growthFactor = this.getGrowthFactor();
		
		float perishFactor = this.getPerishFactor();
		
		int oldStatus = this.status;

		growthThisStep = (growthFactor + perishFactor);

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
	
	public Diet getDiet() {
		
		return diet;
		
	}

	public float getGrowthFactor(){
		
		// TODO: appetite
		
		diet = new Diet(5, this.status);
		
		for (Countable prey: this.area.getDiet(this)){
			
			float dueShare = prey.giveDueShare(this);
			
			diet.addShare(prey.getId(), dueShare);
			
		}
		
		diet.normalizeShares();
		
		return diet.getGrowthFactor();
				
	}
	
	public float giveClaimForShare(Countable askingPrey){
		
		float dueSharesFromOthers = 0;
		
		for (Countable prey: this.area.getDiet(this)){
			
			if (!prey.getId().equals(askingPrey.getId()))
			
				dueSharesFromOthers += prey.giveDueShare(this);
			
		}
		
		float claimedShare = (float)this.status * ((float)askingPrey.status / ((float)askingPrey.status + dueSharesFromOthers));
		
		return claimedShare;
		
	}
	
	public String getId(){
		
		return this.species.getId();
		
	}
	
	public Species getSpecies() {
		
		return species;
		
	}
	
}
