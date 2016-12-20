package mrc.world;

import java.util.HashMap;

import mrc.ecosystem.Population;

public class FantasyWildlifeFund {

	private static Bank bank;
	
	private static HashMap<String, ProtectedSpecies> populationRegistry = new HashMap<String, ProtectedSpecies>();
	
	public static void register(Population population){
		
		String speciesId = population.getSpecies().getId();
		
		ProtectedSpecies registered;
		
		if (populationRegistry.containsKey(speciesId)){
			
			registered = populationRegistry.get(speciesId);
			
			registered.setWorldPopulation(registered.getWorldPopulation() + (population.getStatusShiftThisStep() * population.getCarryingCapacityFactor()));
			
		} else {
			
			registered = new ProtectedSpecies(population.getSpecies());
			
			registered.setWorldPopulation(population.getStatus() * population.getCarryingCapacityFactor());
			
		}
		
	}
	
}
