package mrc.ecosystem;

import java.util.List;

public class Herbivore extends Species {
	
	private List<Resource> diet;

	public Herbivore(int order, int index){
		
		super(order, index);
		
	}

	public List<Resource> getDiet() {
		
		return diet;
		
	}

	public void setDiet(List<Resource> diet) {
		
		this.diet = diet;
		
	}	

}
