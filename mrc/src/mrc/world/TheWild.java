package mrc.world;

import java.util.HashMap;

import mrc.config.GlobalConstants;
import mrc.ecosystem.Carnivore;
import mrc.ecosystem.Ecosystem;
import mrc.ecosystem.Herbivore;
import mrc.ecosystem.Population;
import mrc.ecosystem.Species;
import mrc.geography.Location;

public class TheWild extends Ecosystem {

	private static HashMap<String, Species> knownSpecies = new HashMap<String, Species>();
	
	public TheWild(){
		
		super();
		
	}
		
	public void genesis(){
		
		// FIRST, CREATE ALL KNOWN SPECIES AND THEIR POPULATIONS IN THE WILD
		
		// INSTANTIATE POPULATIONS AND ASSIGN TO AREAS
		
		// Hebivores at Lvl 1
		
		int herbivorePopulation = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[1]);
		
		int lvl1index = 0;
		
		float hunger1Factor = Integer.parseInt(GlobalConstants.HUNGRY_PERCENT[1]) / 100;
		
		int eat1Amount = Integer.parseInt(GlobalConstants.EAT_AMOUNT[1]);		
		
		float vulnerability1Factor = Integer.parseInt(GlobalConstants.VULNERABLE_PERCENT[1]) / 100;
		
		float growth1Factor = Integer.parseInt(GlobalConstants.GROWTH_PERCENT[1]) / 100;
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_1.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_1[i];
			
			String[] areas = conf.split(",");
			
			for (int j=0; j<areas.length; j++){
				
				Herbivore herbivore = new Herbivore(1, lvl1index++);
				
				TheWild.knownSpecies.put(herbivore.getId(), herbivore);
												
				int areaIndex = Integer.parseInt(areas[j]);
				
				Population population = new Population(herbivore, this, herbivorePopulation, this.getArea(areaIndex), Location.SOUTH, hunger1Factor, eat1Amount, vulnerability1Factor, growth1Factor);
				
				this.addNewPopulation(population);
				
			}
			
		}		
		
		// Carnivores at Lvl 2
		
		int carnivore2Population = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[2]);
		
		int lvl2index = 0;
		
		float hunger2Factor = Integer.parseInt(GlobalConstants.HUNGRY_PERCENT[2]) / 100;
		
		int eat2Amount = Integer.parseInt(GlobalConstants.EAT_AMOUNT[2]);		

		float vulnerability2Factor = Integer.parseInt(GlobalConstants.VULNERABLE_PERCENT[2]) / 100;	
		
		float growth2Factor = Integer.parseInt(GlobalConstants.GROWTH_PERCENT[2]) / 100;		
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_2.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_2[i];
			
			String[] areas = conf.split(",");
			
			for (int j=0; j<areas.length; j++){
				
				Carnivore carnivore = new Carnivore(2, lvl2index++);
				
				TheWild.knownSpecies.put(carnivore.getId(), carnivore);
												
				int areaIndex = Integer.parseInt(areas[j]);
				
				Population population;
				
				if (areas.length == 1 || j == 1){
					
					population = new Population(carnivore, this, carnivore2Population, this.getArea(areaIndex), Location.INNER, hunger2Factor, eat2Amount, vulnerability2Factor, growth2Factor);
					
				} else if (j < 1){
					
					population = new Population(carnivore, this, carnivore2Population, this.getArea(areaIndex), Location.WEST, hunger2Factor, eat2Amount, vulnerability2Factor, growth2Factor);
					
				} else {
					
					population = new Population(carnivore, this, carnivore2Population, this.getArea(areaIndex), Location.EAST, hunger2Factor, eat2Amount, vulnerability2Factor, growth2Factor);
					
				}
				
				this.addNewPopulation(population);
				
			}
			
		}		
		
		// Carnivores at Lvl 3
		
		int carnivore3Population = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[3]);
		
		int lvl3index = 0;
		
		float hunger3Factor = Integer.parseInt(GlobalConstants.HUNGRY_PERCENT[3]) / 100;
		
		int eat3Amount = Integer.parseInt(GlobalConstants.EAT_AMOUNT[3]);		
		
		float vulnerability3Factor = Integer.parseInt(GlobalConstants.VULNERABLE_PERCENT[3]) / 100;		
		
		float growth3Factor = Integer.parseInt(GlobalConstants.GROWTH_PERCENT[3]) / 100;	
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_3.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_3[i];
			
			String[] areas = conf.split(",");
			
			for (int j=0; j<areas.length; j++){
				
				Carnivore carnivore = new Carnivore(3, lvl3index++);
				
				TheWild.knownSpecies.put(carnivore.getId(), carnivore);
				
				int areaIndex = Integer.parseInt(areas[j]);
				
				Population population = new Population(carnivore, this, carnivore3Population, this.getArea(areaIndex), Location.NORTH, hunger3Factor, eat3Amount, vulnerability3Factor, growth3Factor);
				
				this.addNewPopulation(population);
				
			}
			
		}		
		
		// Carnivores at Lvl 4
		
		int carnivore4Population = Integer.parseInt(GlobalConstants.NEW_SPAWN_POPULATIONS[4]);
		
		int lvl4index = 0;
		
		float hunger4Factor = Integer.parseInt(GlobalConstants.HUNGRY_PERCENT[4]) / 100;
		
		int eat4Amount = Integer.parseInt(GlobalConstants.EAT_AMOUNT[4]);		
		
		float vulnerability4Factor = Integer.parseInt(GlobalConstants.VULNERABLE_PERCENT[4]) / 100;		
		
		float growth4Factor = Integer.parseInt(GlobalConstants.GROWTH_PERCENT[4]) / 100;		
		
		for (int i=0; i<GlobalConstants.AREAS_LVL_4.length; i++){
			
			String conf = GlobalConstants.AREAS_LVL_4[i];
			
			String[] areas = conf.split(",");
			
			for (int j=0; j<areas.length; j++){
				
				Carnivore carnivore = new Carnivore(4, lvl4index++);
				
				TheWild.knownSpecies.put(carnivore.getId(), carnivore);
				
				int areaIndex = Integer.parseInt(areas[j]);
				
				Population population = new Population(carnivore, this, carnivore4Population, this.getArea(areaIndex), Location.OVER, hunger4Factor, eat4Amount, vulnerability4Factor, growth4Factor);
			
				this.addNewPopulation(population);
				
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
