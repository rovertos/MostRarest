package mrc.world;

import java.util.HashMap;

import mrc.ecosystem.Population;

public class FantasyWildlifeFund {

	private static Bank bank;
	
	private static HashMap<String, RegisteredSpecies> populationRegistry = new HashMap<String, RegisteredSpecies>();
	
	public static void register(Population population){
		
		String speciesId = population.getSpecies().getId();
		
		RegisteredSpecies registered;
		
		if (populationRegistry.containsKey(speciesId)){
			
			registered = populationRegistry.get(speciesId);
			
			registered.setWorldPopulation(registered.getWorldPopulation() + population.getShift());
			
		} else {
			
			registered = new RegisteredSpecies(population.getSpecies());
			
			registered.setWorldPopulation(population.getTotal());
			
		}
		
	}
	
}
