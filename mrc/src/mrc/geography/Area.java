package mrc.geography;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mrc.ecosystem.Countable;
import mrc.ecosystem.Population;
import mrc.ecosystem.Resource;

public class Area {
	
	private HashMap<Location, Countable> populationMap = new HashMap<Location, Countable>();
	
	private HashMap<Location, Area> borderMap = new HashMap<Location, Area>();
	
	public void settlePopulation(Location location, Countable population){
		
		populationMap.put(location, population);
		
	}
	
	public void borderArea(Location location, Area area){
		
		borderMap.put(location, area);
		
	}
	
	public List<Countable> getDiet(Countable countable){
		
		ArrayList<Countable> diet = new ArrayList<Countable>();
		
		if (isPopulationLocated(countable, Location.UNDER)){
			
			// DO NOTHING: RESOURCES DO NOT EAT
			
		} else if (isPopulationLocated(countable, Location.OVER)) {
			
			if (populationMap.containsKey(Location.NORTH)){
				
				diet.add(populationMap.get(Location.NORTH));
				
			}
			
			if (borderMap.containsKey(Location.EAST) && borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.NORTH)){
				
				diet.add(borderMap.get(Location.EAST).getPopulationMap().get(Location.NORTH));
				
			}			
				
			if (borderMap.containsKey(Location.WEST) && borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.NORTH)){
				
				diet.add(borderMap.get(Location.WEST).getPopulationMap().get(Location.NORTH));
				
			}			
			
		} else if (isPopulationLocated(countable, Location.NORTH)){
			
			if (populationMap.containsKey(Location.INNER))

				diet.add(populationMap.get(Location.INNER));

			if (populationMap.containsKey(Location.EAST)){
				
				diet.add(populationMap.get(Location.EAST));
				
			} else if (borderMap.containsKey(Location.EAST) && borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.WEST)){
				
				diet.add(borderMap.get(Location.EAST).getPopulationMap().get(Location.WEST));
				
			}
			
			if (populationMap.containsKey(Location.WEST)){
				
				diet.add(populationMap.get(Location.WEST));
				
			} else if (borderMap.containsKey(Location.WEST) && borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.EAST)){
				
				diet.add(borderMap.get(Location.WEST).getPopulationMap().get(Location.EAST));
				
			}			
			
		} else if (isPopulationLocated(countable, Location.SOUTH)) {
			
			if (populationMap.containsKey(Location.UNDER)){
				
				diet.add(populationMap.get(Location.UNDER));
				
			}
			
			if (borderMap.containsKey(Location.EAST) && borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.UNDER)){
				
				diet.add(borderMap.get(Location.EAST).getPopulationMap().get(Location.UNDER));
				
			}			
				
			if (borderMap.containsKey(Location.WEST) && borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.UNDER)){
				
				diet.add(borderMap.get(Location.WEST).getPopulationMap().get(Location.UNDER));
				
			}			
			
		} else if (countable != null) {
			
			if (populationMap.containsKey(Location.SOUTH))

				diet.add(populationMap.get(Location.SOUTH));

			if (isPopulationLocated(countable, Location.EAST) && borderMap.containsKey(Location.EAST)) {

				if (borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.SOUTH))

					diet.add((Population)borderMap.get(Location.EAST).getPopulationMap().get(Location.SOUTH));

			} else if (isPopulationLocated(countable, Location.WEST) && borderMap.containsKey(Location.WEST)) {

				if (borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.SOUTH))

					diet.add((Population)borderMap.get(Location.WEST).getPopulationMap().get(Location.SOUTH));

			}			
			
		}
		
		return diet;
		
	}
	
	public List<Population> getPredators(Countable population){
		
		ArrayList<Population> predators = new ArrayList<Population>();
		
		if (isPopulationLocated(population, Location.UNDER)){
			
			if (populationMap.containsKey(Location.SOUTH)){
				
				predators.add((Population)populationMap.get(Location.SOUTH));
				
			}
			
			if (borderMap.containsKey(Location.EAST) && borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.SOUTH)){
				
				predators.add((Population)borderMap.get(Location.EAST).getPopulationMap().get(Location.SOUTH));
				
			}			
				
			if (borderMap.containsKey(Location.WEST) && borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.SOUTH)){
				
				predators.add((Population)borderMap.get(Location.WEST).getPopulationMap().get(Location.SOUTH));
				
			}			
			
		} else if (isPopulationLocated(population, Location.OVER)){
			
			// DO NOTHING: NO PREDATORS FOR APEX CREATURES
			
		} else if (isPopulationLocated(population, Location.NORTH)){
			
			if (populationMap.containsKey(Location.OVER)){
				
				predators.add((Population)populationMap.get(Location.OVER));
				
			}
			
			if (borderMap.containsKey(Location.EAST) && borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.OVER)){
				
				predators.add((Population)borderMap.get(Location.EAST).getPopulationMap().get(Location.OVER));
				
			}			
				
			if (borderMap.containsKey(Location.WEST) && borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.OVER)){
				
				predators.add((Population)borderMap.get(Location.WEST).getPopulationMap().get(Location.OVER));
				
			}				
			
		} else if (isPopulationLocated(population, Location.SOUTH)) {

			if (populationMap.containsKey(Location.INNER))

				predators.add((Population)populationMap.get(Location.INNER));

			if (populationMap.containsKey(Location.EAST)){
				
				predators.add((Population)populationMap.get(Location.EAST));
				
			} else if (borderMap.containsKey(Location.EAST) && borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.WEST)){
				
				predators.add((Population)borderMap.get(Location.EAST).getPopulationMap().get(Location.WEST));
				
			}
			
			if (populationMap.containsKey(Location.WEST)){
				
				predators.add((Population)populationMap.get(Location.WEST));
				
			} else if (borderMap.containsKey(Location.WEST) && borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.EAST)){
				
				predators.add((Population)borderMap.get(Location.WEST).getPopulationMap().get(Location.EAST));
				
			}			

		} else if (population != null){
			
			if (populationMap.containsKey(Location.NORTH))

				predators.add((Population)populationMap.get(Location.NORTH));			

			if (isPopulationLocated(population, Location.EAST) && borderMap.containsKey(Location.EAST)) {

				if (borderMap.get(Location.EAST).getPopulationMap().containsKey(Location.NORTH))

					predators.add((Population)borderMap.get(Location.EAST).getPopulationMap().get(Location.NORTH));

			} else if (isPopulationLocated(population, Location.WEST) && borderMap.containsKey(Location.WEST)) {

				if (borderMap.get(Location.WEST).getPopulationMap().containsKey(Location.NORTH))

					predators.add((Population)borderMap.get(Location.WEST).getPopulationMap().get(Location.NORTH));

			}
			
		}
		
		return predators;
		
	}

	private boolean isPopulationLocated(Countable countable, Location location){
		
		return populationMap.containsKey(location) && populationMap.get(location).equals(countable);
		
	}

	public HashMap<Location, Countable> getPopulationMap() {
		
		return populationMap;
		
	}
	
}
