package mrc.ecosystem;

import mrc.config.GlobalConstants;

public class Resource {

	private int level;
	
	private int index;
	
	private int total;
	
	public Resource(int level, int index, int total){
		
		this.level = level;
		
		this.index = index;
		
		this.total = total;
		
	}
	
	public String getId(){
		
		return GlobalConstants.LEVELS[this.level] + index;
		
	}

	public int getTotal() {
		
		return total;
		
	}	
	
}
