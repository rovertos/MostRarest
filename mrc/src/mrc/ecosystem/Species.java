package mrc.ecosystem;

import mrc.config.GlobalConstants;

public class Species {

	private int level;
	
	private int index;
	
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
	
}
