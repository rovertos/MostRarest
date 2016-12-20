package mrc.ecosystem;

import mrc.config.Global;
import mrc.geography.Area;
import mrc.geography.Location;
import mrc.world.FantasyWildlifeFund;

public class Resource extends Countable {

	private int level;
	
	private int index;
	
	public Resource(int index, int status, Area area, float growthThreshold, int carryingCapacityFactor){
		
		super(status, area, Location.UNDER, growthThreshold, carryingCapacityFactor);
		
		this.level = 0;
		
		this.index = index;
		
	}
	
	public void executeStep(StringBuffer buf) {

		float growthFactor = this.getGrowthFactor();

		growthThisStep = growthFactor * status;

		accumulatedGrowth += growthThisStep;
		
		buf.append(this.getId() + ": " + status + "[" + Global.formatter.format(growthFactor) + "] => " + Global.formatter.format(growthThisStep) + " + " + Global.formatter.format(accumulatedGrowth) + ". ");

	}	
	
	public void heal(float healAmount, StringBuffer buf){
		
		accumulatedGrowth += healAmount;
		
		if (Math.abs(accumulatedGrowth) > growthThreshold) {

			if (accumulatedGrowth > 0 && status < 5) {

				status++;
				
				accumulatedGrowth = accumulatedGrowth - growthThreshold;

			} else if (accumulatedGrowth < 0 && status > 1) {
				
				status--;
				
				accumulatedGrowth = growthThreshold + accumulatedGrowth;
				
			}

		}
		
		buf.append(this.getId() + ": +" + Global.formatter.format(healAmount) + " => " + this.status + ". ");
		
	}
	
	public float getGrowthFactor(){
		
		float growthFactor = 0;
		
		for (Population predator: this.area.getPredators(this)){
			
			Float share = lastGivenShares.get(predator.getId());
			
			growthFactor -= share.floatValue();
			
		}
		
		lastGivenShares.clear();
		
		return growthFactor;
		
	}
	
	public float giveClaimForShare(Countable countable){
		
		// NOTHING TO CLAIM
		
		return 0;
		
	}	
	
	public String getId(){
		
		return Global.LEVELS[this.level] + index;
		
	}
	
}
