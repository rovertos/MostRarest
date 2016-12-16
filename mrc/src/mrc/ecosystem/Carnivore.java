package mrc.ecosystem;

import java.util.List;

public class Carnivore extends Species {
	
	private List<Species> prey;

	public Carnivore(int order, int index){
		
		super(order, index);
		
	}

	public List<Species> getPrey() {
		
		return prey;
		
	}

	public void setPrey(List<Species> prey) {
		
		this.prey = prey;
		
	}	
	
}
