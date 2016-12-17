package mrc.ecosystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mrc.config.GlobalConstants;
import mrc.geography.Area;
import mrc.geography.Location;

public class Ecosystem {
	
	private HashMap<String, Population> populations = new HashMap<String, Population>();
	
	private List<Resource> resources;
	
	private List<Area> areas;
	
	public Ecosystem(){
		
		this.areas = new ArrayList<Area>();
		
		for (int i=0; i<7; i++){
			
			Area area = new Area();
			
			if (i > 0){
				
				this.areas.get(i-1).borderArea(Location.EAST, area);
				
				area.borderArea(Location.WEST, this.areas.get(i-1));
				
			}
			
			this.areas.add(area);
			
		}
		
		// INSTANTIATE RESOURCES AND ASSIGN TO AREAS 
		
		int newPopulation = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[0]);
		
		int lvl0index = 0;
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_0.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_0[i];
			
			String[] instances = conf.split(",");
			
			for (int j=0; j<instances.length; j++){
				
				Resource resource = new Resource(0, lvl0index++, newPopulation);
				
				String[] assignments = instances[i].split("-");
				
				for (int k=0; k<assignments.length; k++){
				
					int areaIndex = Integer.parseInt(assignments[k]);
					
					areas.get(areaIndex).getResources().add(resource);
				
				}
				
			}
			
		}
		
	}
	
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
	
	public Area getArea(int index) {
		
		if (index < areas.size())
			
			return this.areas.get(index);
		
		else
			
			return null;
		
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
