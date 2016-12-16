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

public class TheWild extends Ecosystem {

	private static HashMap<String, Species> knownSpecies = new HashMap<String, Species>();
		
	public void create(){
		
		ArrayList<Resource> resources = new ArrayList<Resource>();
		
		// FIRST, CREATE ALL KNOWN SPECIES AND THEIR POPULATIONS IN THE WILD
		
		for (int i=0; i<GlobalConstants.WILD_POPULATIONS.length; i++){
			
			int N = Integer.parseInt(GlobalConstants.SPECIES_PER_LEVEL[i]);
			
			for (int j=1; j<=N; j++){
				
				if (i == 0){
					
					int wildPopulation = Integer.parseInt(GlobalConstants.WILD_POPULATIONS[i]);	
					
					Resource resource = new Resource(i, j, wildPopulation);
					
					resources.add(resource);
					
				} else if (i == 1){
					
					Herbivore herbivore = new Herbivore(i, j);
					
					TheWild.knownSpecies.put(herbivore.getId(), herbivore);
					
					int wildPopulation = Integer.parseInt(GlobalConstants.WILD_POPULATIONS[i]);
					
					Population population = new Population(herbivore, this, wildPopulation);
					
					this.addNewPopulation(population);
					
				} else {
					
					Carnivore carnivore = new Carnivore(i, j);
					
					TheWild.knownSpecies.put(carnivore.getId(), carnivore);
					
					int wildPopulation = Integer.parseInt(GlobalConstants.WILD_POPULATIONS[i]);
					
					Population population = new Population(carnivore, this, wildPopulation);
					
					this.addNewPopulation(population);
					
				}
				
			}
			
		}
		
		// THEN DESTABILIZE THE WORLD ECOSYSTEM, TO CREATE DYNAMICS
		
		for (int i=0; i<GlobalConstants.INSTABILITIES.length; i++){
			
			String unstableSpeciesId = GlobalConstants.INSTABILITIES[i][0];
			
			int unstablePopulationTotal = Integer.parseInt(GlobalConstants.INSTABILITIES[i][1]);
			
			this.getPopulation(unstableSpeciesId).setTotal(unstablePopulationTotal);
			
		}
		
		// FINALLY, LET NATURE DECIDE WHAT EACH SPECIES PREYS ON
		
	}
	
	public static Species getSpecies(String id){
		
		return knownSpecies.get(id);
		
	}
	
}
