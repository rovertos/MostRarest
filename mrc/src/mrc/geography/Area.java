package mrc.geography;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mrc.ecosystem.Population;
import mrc.ecosystem.Resource;

public class Area {
	
	private Population apex;
	
	private List<Resource> resources;
	
	private HashMap<Location, Population> populationMap = new HashMap<Location, Population>();
	
	private HashMap<Location, Area> borderMap = new HashMap<Location, Area>();
	
	public Area(){
		
		this.resources = new ArrayList<Resource>();
		
	}

	public void settlePopulation(Location location, Population population){
		
		populationMap.put(location, population);
		
	}
	
	public void borderArea(Location location, Area area){
		
		borderMap.put(location, area);
		
	}	
	
	public List<Population> getPredators(Population population){
		
		ArrayList<Population> predators = new ArrayList<Population>();
		
		if (isPopulationLocated(population, Location.NORTH)){
			
			if (populationMap.containsKey(Location.OVER)){
				
				predators.add(populationMap.get(Location.OVER));
				
			}
			
			if (borderMap.containsKey(Location.EAST) && borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.OVER)){
				
				predators.add(borderMap.get(Location.EAST).getPopulationMap().get(Location.OVER));
				
			}			
				
			if (borderMap.containsKey(Location.WEST) && borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.OVER)){
				
				predators.add(borderMap.get(Location.WEST).getPopulationMap().get(Location.OVER));
				
			}				
			
		} else if (isPopulationLocated(population, Location.SOUTH)) {

			if (populationMap.containsKey(Location.INNER))

				predators.add(populationMap.get(Location.INNER));

			if (populationMap.containsKey(Location.EAST)){
				
				predators.add(populationMap.get(Location.EAST));
				
			} else if (borderMap.containsKey(Location.EAST) && borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.WEST)){
				
				predators.add(borderMap.get(Location.EAST).getPopulationMap().get(Location.WEST));
				
			}
			
			if (populationMap.containsKey(Location.WEST)){
				
				predators.add(populationMap.get(Location.WEST));
				
			} else if (borderMap.containsKey(Location.WEST) && borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.EAST)){
				
				predators.add(borderMap.get(Location.WEST).getPopulationMap().get(Location.EAST));
				
			}			

		} else {
			
			if (populationMap.containsKey(Location.NORTH))

				predators.add(populationMap.get(Location.NORTH));			

			if (isPopulationLocated(population, Location.EAST) && borderMap.containsKey(Location.EAST)) {

				if (borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.NORTH))

					predators.add(borderMap.get(Location.EAST).getPopulationMap().get(Location.NORTH));

			} else if (isPopulationLocated(population, Location.WEST) && borderMap.containsKey(Location.WEST)) {

				if (borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.NORTH))

					predators.add(borderMap.get(Location.WEST).getPopulationMap().get(Location.NORTH));

			}
			
		}
		
		return predators;
		
	}	

	public List<Resource> getResources() {
		
		return resources;
		
	}

	public void setResources(List<Resource> resources) {
		
		this.resources = resources;
		
	}

	private boolean isPopulationLocated(Population population, Location location){
		
		return populationMap.containsKey(location) && populationMap.get(location).equals(population);
		
	}

	public HashMap<Location, Population> getPopulationMap() {
		
		return populationMap;
		
	}
	
}
