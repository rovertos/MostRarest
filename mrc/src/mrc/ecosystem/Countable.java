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
	
	private Location location;
	
	public Countable(int status, Area area, Location location, float growthThreshold, int carryingCapacityFactor){
		
		this.status = status;
		
		this.area = area;
		
		this.growthThreshold = growthThreshold;
		
		this.carryingCapacityFactor = carryingCapacityFactor;
		
		this.area.settlePopulation(location, this);
		
	}
	
	public abstract float getGrowthFactor();
	
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

	public Area getArea() {
		
		return area;
		
	}

	public Location getLocation() {
		
		return location;
		
	}	
	
}
