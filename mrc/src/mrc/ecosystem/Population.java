package mrc.ecosystem;

import mrc.geography.Area;
import mrc.geography.Location;
import mrc.world.FantasyWildlifeFund;

public class Population implements Countable {
	
	private int total;
	
	private int shift;

	private Species species;
	
	private Ecosystem ecosystem;
	
	private Area area;
	
	public Population(Species species, Ecosystem ecosystem, int total, Area area, Location location){
		
		this.species = species;
		
		this.ecosystem = ecosystem;
		
		this.total = total;
		
		this.area = area;
		
		area.settlePopulation(location, this);
		
		FantasyWildlifeFund.register(this);
		
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
	
}
