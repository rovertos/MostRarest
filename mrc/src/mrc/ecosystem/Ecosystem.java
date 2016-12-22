package mrc.ecosystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mrc.config.Global;
import mrc.geography.Area;
import mrc.geography.Location;
import mrc.util.Logger;

public class Ecosystem {
	
	private HashMap<String, Population> populations = new HashMap<String, Population>();
	
	private List<Area> areas;
	
	private List<Resource> resources;
	
	protected int carryingCapacityFactor;
	
	public Ecosystem(int carryingCapacityFactor){
		
		this.carryingCapacityFactor = carryingCapacityFactor;
		
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
		
		resources = new ArrayList<Resource>();
		
		int status = Integer.parseInt(Global.NEW_SPAWN_STATUSES[0]);
		
		float growthThreshold = Float.parseFloat(Global.GROWTH_THRESHOLDS[0]);
		
		int index = 0;
		
		for (int i=0; i<Global.AREAS_LVL_0.length; i++){
			
			String conf = Global.AREAS_LVL_0[i];
			
			String[] areasString = conf.split(",");
			
			for (int j=0; j<areasString.length; j++){
				
				int areaIndex = Integer.parseInt(areasString[j]);
				
				Area area = areas.get(areaIndex);
				
				Resource resource = new Resource(index++, status, area, growthThreshold, this.carryingCapacityFactor);
				
				resources.add(resource);
				
			}
			
		}
		
	}
	
	public void executeStep(){
		
		// First, Species eat and reproduce
				
		for (int i=Global.LEVELS.length-1; i>0; i--){

			int pops = Integer.parseInt(Global.POPS_PER_LEVEL[i]);
			
			for (int j=0; j<pops; j++){
				
				String id = Global.LEVELS[i] + j;
								
				Population population = populations.get(id);
				
				population.executeStep();
				
			}
			
		}
		
		// Then, Resources heal
		
		for (Resource resource: resources){
			
			resource.executeStep();
			
		}
		
		float totalGrowthThisStep = 0;
		
		for (Resource resource: resources){
			
			totalGrowthThisStep += resource.getGrowthThisStep();
			
		}
		
		for (Resource resource: resources){
			
			resource.heal(-1 * totalGrowthThisStep / 3);
			
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
	
	public List<Area> getAreas() {
		
		return areas;

	}

	public Population getPopulation(String id){
		
		return populations.get(id);
		
	}
	
}
