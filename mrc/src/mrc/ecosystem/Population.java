package mrc.ecosystem;

import mrc.geography.Area;
import mrc.geography.Location;
import mrc.world.FantasyWildlifeFund;

public class Population implements Countable {
	
	private int total = 0;
	
	private int shift = 0;

	private Species species;
	
	private Ecosystem ecosystem;
	
	private Area area;
	
	private int eatenThisStep = 0;
	
	private float hungerFactor;
	
	private float eatAmount;
	
	private float vulnerabilityFactor;
	
	private float growthFactor;
	
	public Population(Species species, Ecosystem ecosystem, int total, Area area, Location location, float hungerFactor, float eatAmount, float vulnerabilityFactor, float growthFactor){
		
		this.species = species;
		
		this.ecosystem = ecosystem;
		
		this.total = total;
		
		this.area = area;
		
		this.hungerFactor = hungerFactor;
		
		this.eatAmount = eatAmount;
		
		this.vulnerabilityFactor = vulnerabilityFactor;
		
		this.growthFactor = growthFactor;
		
		area.settlePopulation(location, this);
		
		FantasyWildlifeFund.register(this);
		
	}
	
	public void executeStep(StringBuffer buf){
		
		int survivors = total - eatenThisStep;
		
		int hungry = (int) (survivors * hungerFactor);
		
		int amountLeftToEat = (int) (hungry * eatAmount);
		
		buf.append(this.getSpecies().getId() + ":" + survivors + " => ");
		
		// FIRST CONSUME
			
		for (Countable countable: this.getArea().getDiet(this)){
			
			int dueShare = countable.getDueShare(this);
			
			buf.append(countable.getId() + "-" + dueShare + ", ");
			
			countable.eaten(dueShare);
			
			amountLeftToEat = amountLeftToEat - dueShare;
			
		}
		
		buf.setLength(buf.length() - 2);
		
		// TODO: TOTALLY POSSIBLE amountLeftToEat IS NEGATIVE, NEED TO FIX (?)
		
		if (amountLeftToEat < 0)
			
			amountLeftToEat = 0;
		
		int starvedToDeath =  (int) (amountLeftToEat / eatAmount);
		
		survivors = survivors - starvedToDeath;
		
		// THEN REPRODUCE
		
		int newTotal = (int) (survivors * growthFactor);
		
		this.shift = newTotal - total;
		
		buf.append(" => " + (-starvedToDeath) + "[" + amountLeftToEat + "] => " + newTotal + "(" + this.shift + "). ");
		
		this.total = newTotal;
		
		this.eatenThisStep = 0;
		
		FantasyWildlifeFund.register(this);
		
	}
	
	public String getId(){
		
		return this.species.getId();
		
	}
	
	public int getTotal() {
		
		return total;
		
	}
	
	public void setTotal(int total) {
		
		this.total = total;
		
	}	

	public Species getSpecies() {
		
		return species;
		
	}

	public Ecosystem getEcosystem() {
		
		return ecosystem;
		
	}

	public int getShift() {
		
		return shift;
		
	}

	public Area getArea() {
		
		return area;
		
	}	
	
	public void eaten(int eatenThisStep) {
		
		this.eatenThisStep += eatenThisStep;
		
	}	
	
	public int getEatenThisStep() {
		
		return eatenThisStep;
		
	}	

	public float getHungerFactor() {
		
		return hungerFactor;
		
	}		

	public void setHungerFactor(float hungerFactor) {
		
		this.hungerFactor = hungerFactor;
		
	}	

	public float getEatAmount() {
		
		return eatAmount;
		
	}

	public void setEatAmount(float eatAmount) {
		
		this.eatAmount = eatAmount;
		
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

	public float getGrowthFactor() {
		
		return growthFactor;
		
	}

	public void setGrowthFactor(float growthFactor) {
		
		this.growthFactor = growthFactor;
		
	}
	
	public int getCompetitorsForThis(){
		
		return area.getPredators(this).size();
		
	}
	
	public int getAvailableFoods(){
		
		return area.getDiet(this).size();
		
	}	
	
}
