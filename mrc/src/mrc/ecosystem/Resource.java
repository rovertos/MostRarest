package mrc.ecosystem;

import mrc.config.Global;
import mrc.geography.Area;
import mrc.geography.Location;

public class Resource extends Countable {

	private int level;
	
	private int index;
	
	public Resource(int index, int status, Area area, float growthThreshold, int carryingCapacityFactor){
		
		super(status, area, Location.UNDER, growthThreshold, carryingCapacityFactor);
		
		this.level = 0;
		
		this.index = index;
		
	}
	
	public void executeStep() {

		float growthFactor = this.getGrowthFactor();
		
		float perishFactor = this.getPerishFactor();

		growthThisStep = (growthFactor + perishFactor);

		accumulatedGrowth += growthThisStep;
		
	}	
	
	public void heal(float healAmount){
		
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
		
	}
	
	public String getGrowthLog(){
		
		return "";
		
	}
	
	public float getGrowthFactor(){
		
		// HEALING, NOT GROWING
		
		return 0;
		
	}
	
	public float giveClaimForShare(Countable countable){
		
		// NOTHING TO CLAIM
		
		return 0;
		
	}	
	
	public String getId(){
		
		return Global.LEVELS[this.level] + index;
		
	}
	
}
