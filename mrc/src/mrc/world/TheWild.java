package mrc.world;

import java.util.ArrayList;
import java.util.HashMap;

import mrc.config.GlobalConstants;
import mrc.ecosystem.Carnivore;
import mrc.ecosystem.Ecosystem;
import mrc.ecosystem.Herbivore;
import mrc.ecosystem.Population;
import mrc.ecosystem.Resource;
import mrc.ecosystem.Species;
import mrc.geography.Location;

public class TheWild extends Ecosystem {

	private static HashMap<String, Species> knownSpecies = new HashMap<String, Species>();
		
	public void genesis(){
		
		ArrayList<Resource> resources = new ArrayList<Resource>();
		
		// FIRST, CREATE ALL KNOWN SPECIES AND THEIR POPULATIONS IN THE WILD
		
		// INSTANTIATE POPULATIONS AND ASSIGN TO AREAS
		
		// Hebivores at Lvl 1
		
		int herbivorePopulation = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[1]);
		
		int lvl1index = 0;
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_1.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_1[i];
			
			String[] instances = conf.split(",");
			
			for (int j=0; j<instances.length; j++){
				
				Herbivore herbivore = new Herbivore(1, lvl1index++);
				
				TheWild.knownSpecies.put(herbivore.getId(), herbivore);
								
				String[] assignments = instances[i].split("-");
				
				for (int k=0; k<assignments.length; k++){
				
					int areaIndex = Integer.parseInt(assignments[k]);
					
					Population population = new Population(herbivore, this, herbivorePopulation, this.getArea(areaIndex), Location.SOUTH);
					
					this.addNewPopulation(population);
					
				}
				
			}
			
		}		
		
		// Carnivores at Lvl 2
		
		int carnivore2Population = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[2]);
		
		int lvl2index = 0;
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_2.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_2[i];
			
			String[] instances = conf.split(",");
			
			for (int j=0; j<instances.length; j++){
				
				Carnivore carnivore = new Carnivore(2, lvl2index++);
				
				TheWild.knownSpecies.put(carnivore.getId(), carnivore);
								
				String[] assignments = instances[i].split("-");
				
				for (int k=0; k<assignments.length; k++){
				
					int areaIndex = Integer.parseInt(assignments[k]);
					
					Population population;
					
					if (instances.length == 1 || j == 1){
						
						population = new Population(carnivore, this, carnivore2Population, this.getArea(areaIndex), Location.INNER);
						
					} else if (j < 1){
						
						population = new Population(carnivore, this, carnivore2Population, this.getArea(areaIndex), Location.WEST);
						
					} else {
						
						population = new Population(carnivore, this, carnivore2Population, this.getArea(areaIndex), Location.EAST);
						
					}
					
					this.addNewPopulation(population);
					
				}
				
			}
			
		}		
		
		// Carnivores at Lvl 3
		
		int carnivore3Population = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[3]);
		
		int lvl3index = 0;
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_3.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_3[i];
			
			String[] instances = conf.split(",");
			
			for (int j=0; j<instances.length; j++){
				
				Carnivore carnivore = new Carnivore(3, lvl3index++);
				
				TheWild.knownSpecies.put(carnivore.getId(), carnivore);
								
				String[] assignments = instances[i].split("-");
				
				for (int k=0; k<assignments.length; k++){
				
					int areaIndex = Integer.parseInt(assignments[k]);
					
					Population population = new Population(carnivore, this, carnivore3Population, this.getArea(areaIndex), Location.NORTH);
					
					this.addNewPopulation(population);
					
				}
				
			}
			
		}		
		
		// Carnivores at Lvl 4
		
		int carnivore4Population = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[4]);
		
		int lvl4index = 0;
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_4.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_4[i];
			
			String[] instances = conf.split(",");
			
			for (int j=0; j<instances.length; j++){
				
				Carnivore carnivore = new Carnivore(3, lvl4index++);
				
				TheWild.knownSpecies.put(carnivore.getId(), carnivore);
								
				String[] assignments = instances[i].split("-");
				
				for (int k=0; k<assignments.length; k++){
				
					int areaIndex = Integer.parseInt(assignments[k]);
					
					Population population = new Population(carnivore, this, carnivore4Population, this.getArea(areaIndex), Location.OVER);
					
					this.addNewPopulation(population);
					
				}
				
			}
			
		}
		
		// THEN DESTABILIZE THE WORLD ECOSYSTEM, TO CREATE DYNAMICS
		
		for (int i=0; i<GlobalConstants.INSTABILITIES_IN_THE_WILD.length; i++){
			
			String unstableSpeciesId = GlobalConstants.INSTABILITIES_IN_THE_WILD[i][0];
			
			int unstablePopulationTotal = Integer.parseInt(GlobalConstants.INSTABILITIES_IN_THE_WILD[i][1]);
			
			this.getPopulation(unstableSpeciesId).setTotal(unstablePopulationTotal);
			
		}
		
	}
	
	public static Species getSpecies(String id){
		
		return knownSpecies.get(id);
		
	}
	
}
