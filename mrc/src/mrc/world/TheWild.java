package mrc.world;

import java.util.HashMap;

import mrc.config.Global;
import mrc.ecosystem.Carnivore;
import mrc.ecosystem.Ecosystem;
import mrc.ecosystem.Herbivore;
import mrc.ecosystem.Population;
import mrc.ecosystem.Species;
import mrc.geography.Location;

public class TheWild extends Ecosystem {

	private static HashMap<String, Species> knownSpecies = new HashMap<String, Species>();
	
	public TheWild(){
		
		super(Global.THE_WILD_CARRYING_CAPACITY_FACTOR);
		
	}
		
	public void genesis(){
		
		// FIRST, CREATE ALL KNOWN SPECIES AND THEIR POPULATIONS IN THE WILD
		
		// INSTANTIATE POPULATIONS AND ASSIGN TO AREAS
		
		// Hebivores at Lvl 1
		
		int status = Integer.parseInt(Global.NEW_SPAWN_STATUSES[1]);
		
		float growthThreshold = Float.parseFloat(Global.GROWTH_THRESHOLDS[1]);
		
		int index = 0;
		
		for (int i=0; i<Global.AREAS_LVL_1.length; i++){
			
			String conf = Global.AREAS_LVL_1[i];
			
			String[] areas = conf.split(",");
			
			for (int j=0; j<areas.length; j++){
				
				Herbivore herbivore = new Herbivore(1, index++);
				
				TheWild.knownSpecies.put(herbivore.getId(), herbivore);
												
				int areaIndex = Integer.parseInt(areas[j]);
				
				Population population = new Population(herbivore, status, this.getArea(areaIndex), Location.SOUTH, growthThreshold, this.carryingCapacityFactor);
				
				this.addNewPopulation(population);
				
			}
			
		}		
		
		// Carnivores at Lvl 2
		
		status = Integer.parseInt(Global.NEW_SPAWN_STATUSES[2]);
		
		growthThreshold = Float.parseFloat(Global.GROWTH_THRESHOLDS[2]);
		
		index = 0;	
		
		for (int i=0; i<Global.AREAS_LVL_2.length; i++){
			
			String conf = Global.AREAS_LVL_2[i];
			
			String[] areas = conf.split(",");
			
			for (int j=0; j<areas.length; j++){
				
				Carnivore carnivore = new Carnivore(2, index++);
				
				TheWild.knownSpecies.put(carnivore.getId(), carnivore);
												
				int areaIndex = Integer.parseInt(areas[j]);
				
				Population population;
				
				if (areas.length == 1 || j == 1){
					
					population = new Population(carnivore, status, this.getArea(areaIndex), Location.INNER, growthThreshold, this.carryingCapacityFactor);
					
				} else if (j < 1){
					
					population = new Population(carnivore, status, this.getArea(areaIndex), Location.WEST, growthThreshold, this.carryingCapacityFactor);
					
				} else {
					
					population = new Population(carnivore, status, this.getArea(areaIndex), Location.EAST, growthThreshold, this.carryingCapacityFactor);
					
				}
				
				this.addNewPopulation(population);
				
			}
			
		}		
		
		// Carnivores at Lvl 3
		
		status = Integer.parseInt(Global.NEW_SPAWN_STATUSES[3]);
		
		growthThreshold = Float.parseFloat(Global.GROWTH_THRESHOLDS[3]);
		
		index = 0;	
		
		for (int i=0; i<Global.AREAS_LVL_3.length; i++){
			
			String conf = Global.AREAS_LVL_3[i];
			
			String[] areas = conf.split(",");
			
			for (int j=0; j<areas.length; j++){
				
				Carnivore carnivore = new Carnivore(3, index++);
				
				TheWild.knownSpecies.put(carnivore.getId(), carnivore);
				
				int areaIndex = Integer.parseInt(areas[j]);
				
				Population population = new Population(carnivore, status, this.getArea(areaIndex), Location.NORTH, growthThreshold, this.carryingCapacityFactor);
				
				this.addNewPopulation(population);
				
			}
			
		}		
		
		// Carnivores at Lvl 4
		
		status = Integer.parseInt(Global.NEW_SPAWN_STATUSES[4]);
		
		growthThreshold = Float.parseFloat(Global.GROWTH_THRESHOLDS[4]);
		
		index = 0;			
		
		for (int i=0; i<Global.AREAS_LVL_4.length; i++){
			
			String conf = Global.AREAS_LVL_4[i];
			
			String[] areas = conf.split(",");
			
			for (int j=0; j<areas.length; j++){
				
				Carnivore carnivore = new Carnivore(4, index++);
				
				TheWild.knownSpecies.put(carnivore.getId(), carnivore);
				
				int areaIndex = Integer.parseInt(areas[j]);
				
				Population population = new Population(carnivore, status, this.getArea(areaIndex), Location.OVER, growthThreshold, this.carryingCapacityFactor);
			
				this.addNewPopulation(population);
				
			}
			
		}
		
		// THEN DESTABILIZE THE WORLD ECOSYSTEM, TO CREATE DYNAMICS
		
		for (int i=0; i<Global.INSTABILITIES_IN_THE_WILD.length; i++){
			
			String unstableSpeciesId = Global.INSTABILITIES_IN_THE_WILD[i][0];
			
			int unstablePopulationTotal = Integer.parseInt(Global.INSTABILITIES_IN_THE_WILD[i][1]);
			
			Population population = this.getPopulation(unstableSpeciesId);
			
			population.setStatus(unstablePopulationTotal);
			
			// TEMP, TODO: REMOVE
			
			Location location = Location.UNDER;
			
			int pindex = population.getSpecies().getIndex();
			
			if (population.getId().startsWith("b"))
				
				location = Location.SOUTH;
			
			else if (population.getId().startsWith("d"))
				
				location = Location.NORTH;
			
			else if (population.getId().startsWith("e"))
				
				location = Location.OVER;
			
			else if (population.getId().startsWith("c")){
				
				if (pindex % 4 == 0)

					location = Location.WEST;
				
				else if (pindex % 4 == 2)
					
					location = Location.EAST;
				
				else
					
					location = Location.INNER;
				
			}
			
			population.getArea().settlePopulation(location, population);
			
		}
		
	}
	
	public static Species getSpecies(String id){
		
		return knownSpecies.get(id);
		
	}
	
}
