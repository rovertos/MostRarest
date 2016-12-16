package mrc.ecosystem;

import java.util.HashMap;
import java.util.List;

public class Ecosystem {
	
	private HashMap<String, Population> populations = new HashMap<String, Population>();
	
	private List<Resource> resources;
	
	public boolean addNewPopulation(Population population){
		
		boolean isNew;
		
		if (!populations.containsKey(population.getSpecies().getId())){
			
			isNew = true;
			
			populations.put(population.getSpecies().getId(), population);
			
		} else {
			
			isNew = false;
			
		}
		
		return isNew;
		
	}
	
	public Population getPopulation(String id){
		
		return populations.get(id);
		
	}
	
	public List<Resource> getResources() {
		
		return resources;
		
	}

	public void setResources(List<Resource> resources) {
		
		this.resources = resources;
		
	}
	
}
