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
		
		System.out.println("********************************");		
		System.out.println("CALCULATING GROWTH FACTOR FOR " + this.getId());
		
		float growthFactor = 0;
		
		for (Countable prey: this.area.getDiet(this)){
			
			growthFactor += prey.giveDueShare(this);
			
		}
		
		for (Population predator: this.area.getPredators(this)){
			
			Float share = lastGivenShares.get(predator.getId());
			
			growthFactor -= share.floatValue();
			
		}
		
		lastGivenShares.clear();
		
		return growthFactor;
				
	}
	
	public float giveClaimForShare(Countable askingPrey){
		
		System.out.println(askingPrey.getId() + " asks " + this.getId() + " to claim its share");
		
		float otherClaimedShares = 0;
		
		for (Countable prey: this.area.getDiet(this)){
			
			if (!prey.getId().equals(askingPrey.getId()))
			
				otherClaimedShares += prey.giveDueShare(this);
			
		}
		
		float claimedShare = (float)this.status * ((float)askingPrey.status / ((float)askingPrey.status + otherClaimedShares));
		
		System.out.println(this.getId() + " claims share " + claimedShare + " from askingPrey " + askingPrey.getId());
		
		return claimedShare;
		
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
