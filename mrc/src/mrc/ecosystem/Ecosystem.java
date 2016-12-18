package mrc.ecosystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mrc.config.GlobalConstants;
import mrc.geography.Area;
import mrc.geography.Location;

public class Ecosystem {
	
	private HashMap<String, Population> populations = new HashMap<String, Population>();
	
	private List<Area> areas;
	
	private List<Resource> resources;
	
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
		
		resources = new ArrayList<Resource>();
		
		int newAmount = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[0]);
		
		int lvl0index = 0;
		
		float vulnerability0Factor = Integer.parseInt(GlobalConstants.VULNERABLE_PERCENT[0]) / 100;		
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_0.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_0[i];
			
			String[] areasString = conf.split(",");
			
			for (int j=0; j<areasString.length; j++){
				
				int areaIndex = Integer.parseInt(areasString[j]);
				
				Area area = areas.get(areaIndex);
				
				Resource resource = new Resource(0, lvl0index++, newAmount, area, vulnerability0Factor);
				
				resources.add(resource);
				
			}
			
		}
		
	}
	
	public void executeStep(){
		
		// First, Species eat and reproduce
		
		for (int i=GlobalConstants.LEVELS.length-1; i>0; i--){
			
			int pops = Integer.parseInt(GlobalConstants.POPS_PER_LEVEL[i]);
			
			for (int j=1; j<pops; j++){
				
				String id = GlobalConstants.LEVELS[i] + j;
				
				Population population = populations.get(id);
				
				population.executeStep();
				
			}
			
		}
		
		// Then, Resources heal
		
		int totalResourcesEaten = 0;
		
		for (Resource resource: resources){
			
			totalResourcesEaten += resource.getEatenThisStep();
			
		}
		
		for (Resource resource: resources){
			
			resource.heal(-1 * totalResourcesEaten / 3);
			
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
	
	public void print(){
		
		System.out.println("...");
		
		System.out.println("AREA,OVER,POPULATION,SHIFT,NORTH,POPULATION,SHIFT,WEST,POPULATION,SHIFT,INNER,POPULATION,SHIFT,EAST,POPULATION,SHIFT,SOUTH,POPULATION,SHIFT,UNDER,POPULATION,SHIFT");
		
		for (int i=0; i<this.areas.size(); i++){
			
			StringBuffer buff = new StringBuffer();
			
			buff.append(i + ",");
			
			Area area = this.areas.get(i);
			
			for (Location location: Location.values()){
				
				Countable population = area.getPopulationMap().get(location);
				
				if (population instanceof Population){
					
					buff.append(((Population)population).getSpecies().getId() + ",");
					
					buff.append(((Population)population).getTotal() + ",");
					
					buff.append(((Population)population).getShift() + ",");
					
				} else if (population instanceof Resource) {
					
					buff.append(((Resource)population).getId() + ",");
					
					buff.append(((Resource)population).getTotal() + ",");
					
					buff.append(((Resource)population).getShift() + ",");
					
				} else {
					
					buff.append("__,_,_,");
					
				}

			}
			
			System.out.println(buff.substring(0, buff.length()-1));
			
		}
		
	}
	
}
