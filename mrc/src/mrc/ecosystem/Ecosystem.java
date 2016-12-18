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
			
			String[] areasString = conf.split(",");
			
			for (int j=0; j<areasString.length; j++){
				
				Resource resource = new Resource(0, lvl0index++, newPopulation);
				
				int areaIndex = Integer.parseInt(areasString[j]);
				
				areas.get(areaIndex).spreadResource(resource);
				
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
	
	public void print(){
		
		System.out.println("********** ECOSYSTEM **********");
		
		System.out.println("A,OV,D,P,NO,D,P,WE,D,P,IN,D,P,EA,D,P,SO,D,P,UN,D,P");
		
		for (int i=0; i<this.areas.size(); i++){
			
			StringBuffer buff = new StringBuffer();
			
			buff.append(i + ",");
			
			Area area = this.areas.get(i);
			
			for (Location location: Location.values()){
				
				Countable population = area.getPopulationMap().get(location);
				
				if (population instanceof Population){
					
					buff.append(((Population)population).getSpecies().getId() + ",");
					
					buff.append(area.getDiet(population).size() + ",");
					
					buff.append(area.getPredators(population).size() + ",");
					
				} else if (population instanceof Resource) {
					
					buff.append(((Resource)population).getId() + ",");
					
					buff.append(area.getDiet(population).size() + ",");
					
					buff.append(area.getPredators(population).size() + ",");
					
				} else {
					
					buff.append("__,_,_,");
					
				}

			}
			
			System.out.println(buff.substring(0, buff.length()-1));
			
		}
		
	}
	
}
