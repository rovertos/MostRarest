package mrc.ecosystem;

import mrc.config.GlobalConstants;

public class Species {

	private int order;
	
	private int index;
	
	public Species(int order, int index){
		
		this.order = order;
		
		this.index = index;
		
	}
	
	public String getId(){
		
		return GlobalConstants.ORDERS[this.order] + index;
		
	}

	public int getOrder() {
		
		return order;
		
	}

	public int getIndex() {
		
		return index;
		
	}	
	
}
