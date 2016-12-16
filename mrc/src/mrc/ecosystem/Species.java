package mrc.ecosystem;

import java.util.List;

import mrc.config.GlobalConstants;

public class Species {

	private int level;
	
	private int index;
	
	private List<Carnivore> predators; 
	
	public Species(int level, int index){
		
		this.level = level;
		
		this.index = index;
		
	}
	
	public String getId(){
		
		return GlobalConstants.LEVELS[this.level] + index;
		
	}

	public int getLevel() {
		
		return level;
		
	}

	public int getIndex() {
		
		return index;
		
	}

	public List<Carnivore> getPredators() {
		
		return predators;
		
	}

	public void setPredators(List<Carnivore> predators) {
		
		this.predators = predators;
		
	}	
	
}
