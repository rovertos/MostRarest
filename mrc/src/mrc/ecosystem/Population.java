package mrc.ecosystem;

import mrc.world.FantasyWildlifeFund;

public class Population {
	
	private int total;
	
	private int shift;

	private Species species;
	
	private Ecosystem ecosystem;
	
	public Population(Species species, Ecosystem ecosystem, int total){
		
		this.species = species;
		
		this.ecosystem = ecosystem;
		
		this.total = total;
		
		FantasyWildlifeFund.register(this);
		
	}

	public int getTotal() {
		
		return total;
		
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
	
}
