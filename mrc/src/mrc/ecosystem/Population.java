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
	
	public float getGrowthFactor(){
		
		if (Global.INLINE_LOGGING){
			System.out.println("********************************");		
			System.out.println("CALCULATING GROWTH FACTOR FOR " + this.getId());
		}
		
		float growthFactor = 0;
		
		for (Countable prey: this.area.getDiet(this)){
			
			growthFactor += prey.giveDueShare(this);
			
		}
		
		return growthFactor;
				
	}
	
	public float giveClaimForShare(Countable askingPrey){
		
		if (Global.INLINE_LOGGING)
			System.out.println(askingPrey.getId() + " asks " + this.getId() + " to claim its share");
		
		float dueSharesFromOthers = 0;
		
		for (Countable prey: this.area.getDiet(this)){
			
			if (!prey.getId().equals(askingPrey.getId()))
			
				dueSharesFromOthers += prey.giveDueShare(this);
			
		}
		
		float claimedShare = (float)this.status * ((float)askingPrey.status / ((float)askingPrey.status + dueSharesFromOthers));
		
		if (Global.INLINE_LOGGING)
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
