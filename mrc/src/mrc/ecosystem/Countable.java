package mrc.ecosystem;

import mrc.geography.Area;
import mrc.geography.Location;

public abstract class Countable {
	
	// status: 1-5
	protected int status;
	
	private int statusShiftThisStep;
	
	protected float accumulatedGrowth;
	
	protected float growthThisStep;
	
	protected float growthThreshold;
	
	protected int carryingCapacityFactor;
	
	protected Area area;
	
	public Countable(int status, Area area, Location location, float growthThreshold, int carryingCapacityFactor){
		
		this.status = status;
		
		this.area = area;
		
		this.growthThreshold = growthThreshold;
		
		this.carryingCapacityFactor = carryingCapacityFactor;
		
		this.area.settlePopulation(location, this);
		
	}
	
	public abstract float getGrowthFactor();
	
	public abstract float getDueShare(Countable countable);
	
	public abstract float giveDueShare(Population population);
	
	public abstract String getId();
	
	public int getStatus(){
		
		return status;
		
	}	
	
	public void setStatus(int status) {
		
		this.status = status;
		
	}	

	public int getStatusShiftThisStep() {
		
		return statusShiftThisStep;
		
	}

	public void setStatusShiftThisStep(int shiftStatusThisStep) {
		
		this.statusShiftThisStep = shiftStatusThisStep;
		
	}

	public float getGrowthThisStep(){
	
		return growthThisStep;
		
	}
	
	public int getCompetitorsForThis(){
		
		return area.getPredators(this).size();
		
	}

	public int getCarryingCapacityFactor() {
		
		return carryingCapacityFactor;
		
	}

	public void setCarryingCapacityFactor(int carryingCapacityFactor) {
		
		this.carryingCapacityFactor = carryingCapacityFactor;
		
	}
	
	public int getProjectedStepsForStatusShift(){
		
		if (growthThisStep == 0)
			
			return 0;
		
		boolean positive = growthThisStep > 0;
		
		float remain = (growthThreshold - Math.abs(accumulatedGrowth));
		
		int steps = remain <= Math.abs(growthThisStep) ? 1 : (int)Math.ceil(remain / growthThisStep);
		
		return positive ? steps : -steps;
		
	}
	
}
