package mrc.ecosystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mrc.config.GlobalConstants;
import mrc.geography.Area;
import mrc.geography.Location;
import mrc.util.Logger;

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
		
		float vulnerability0Factor = Float.parseFloat(GlobalConstants.VULNERABLE_PERCENT[0]) / 100;		
		
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

			StringBuffer buf = new StringBuffer();
			
			int pops = Integer.parseInt(GlobalConstants.POPS_PER_LEVEL[i]);
			
			for (int j=0; j<pops; j++){
				
				String id = GlobalConstants.LEVELS[i] + j;
								
				Population population = populations.get(id);
				
				population.executeStep(buf);
				
			}
						
			System.out.println(buf.toString());
			
		}
		
		// Then, Resources heal
		
		int totalResourcesEaten = 0;
		
		for (Resource resource: resources){
			
			totalResourcesEaten += resource.getEatenThisStep();
			
		}
		
		for (Resource resource: resources){
			
			resource.heal(Math.round((float)totalResourcesEaten / 3));
			
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
				
		Logger log = new Logger();
		
		log.setTabLength(15);
		
		String separator = log.getSeparator(" ", "-", 23);
		
		System.out.println(separator);
		
		System.out.println("");
		
		StringBuffer buf = new StringBuffer();
		
		// System.getProperty("line.separator")
		
		buf.append(log.getPrettyPrint("OVER:"));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint(""));
			
			buf.append(log.getPrettyPrint(areas.get(i), Location.OVER));
			
			buf.append(log.getPrettyPrint(""));
			
		}
		
		buf.append(System.getProperty("line.separator"));
		
		buf.append(log.getPrettyPrint("NORTH:"));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint(""));
			
			buf.append(log.getPrettyPrint(areas.get(i), Location.NORTH));
			
			buf.append(log.getPrettyPrint(""));
			
		}		
		
		buf.append(System.getProperty("line.separator"));
		
		buf.append(log.getPrettyPrint("W/IN/E:"));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint(areas.get(i), Location.WEST));
			
			buf.append(log.getPrettyPrint(areas.get(i), Location.INNER));
			
			buf.append(log.getPrettyPrint(areas.get(i), Location.EAST));
			
		}
		
		buf.append(System.getProperty("line.separator"));
		
		buf.append(log.getPrettyPrint("SOUTH:"));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint(""));
			
			buf.append(log.getPrettyPrint(areas.get(i), Location.SOUTH));
			
			buf.append(log.getPrettyPrint(""));
			
		}
		
		buf.append(System.getProperty("line.separator"));
		
		buf.append(log.getPrettyPrint("UNDER:"));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint(""));
			
			buf.append(log.getPrettyPrint(areas.get(i), Location.UNDER));
			
			buf.append(log.getPrettyPrint(""));
			
		}
		
		System.out.println(buf.toString());
		
		System.out.println(separator);
		
	}
	
}
