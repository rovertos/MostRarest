package mrc.ecosystem;

import mrc.config.GlobalConstants;
import mrc.geography.Area;

public class Resource implements Countable {

	private int level;
	
	private int index;
	
	private int total;
	
	private int shift;
	
	private float vulnerabilityFactor;
	
	private Area area;
	
	private int eatenThisStep = 0;
	
	public Resource(int level, int index, int total, Area area, float vulnerabilityFactor){
		
		this.level = level;
		
		this.index = index;
		
		this.total = total;
		
		this.area = area;
		
		this.vulnerabilityFactor = vulnerabilityFactor;
		
		area.spreadResource(this);
		
	}
	
	public void heal(int healAmount){
		
		int survivors = this.total - this.eatenThisStep;
		
		int newTotal = survivors + healAmount;
		
		this.shift = newTotal - this.total;
		
		this.total = newTotal;
		
		this.eatenThisStep = 0;
		
	}
	
	public String getId(){
		
		return GlobalConstants.LEVELS[this.level] + index;
		
	}

	public int getTotal() {
		
		return total;
		
	}

	public int getShift() {
		
		return shift;
		
	}
	
	public int getNumberOfPredators(){
		
		return area.getPredators(this).size();
		
	}	

	public void eaten(int eatenThisStep) {
		
		this.eatenThisStep += eatenThisStep;
		
	}	

	public int getEatenThisStep() {
		
		return eatenThisStep;
		
	}

	public int getDueShare(Population predator) {
		
		float predatorPopulation = predator.getTotal() / predator.getAvailableFoods();
		
		float competitionTotal = 0;
		
		for (Population population: area.getPredators(this)){
			
			competitionTotal += population.getTotal() / population.getAvailableFoods();
			
		}
		
		// TODO: REMOVE EXTINCT POPULATIONS
		
		if (competitionTotal == 0)
			
			competitionTotal = 1;		
		
		float competitionFactor = predatorPopulation / competitionTotal;
		
		return Math.round(total * vulnerabilityFactor * competitionFactor);
		
	}

	public void setVulnerabilityFactor(float vulnerabilityFactor) {
		
		this.vulnerabilityFactor = vulnerabilityFactor;
		
	}
	
	public int getCompetitorsForThis(){
		
		return area.getPredators(this).size();
		
	}	
	
}
