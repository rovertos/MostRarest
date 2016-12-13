package mrc.ecosystem;

import java.util.ArrayList;
import java.util.HashMap;

import mrc.config.GlobalConstants;
import mrc.config.Nature;

public class Ecosystem {
	
	private HashMap<String,Consumable> consumables = new HashMap<String,Consumable>();
	
	private ArrayList<ApexCreature> apexCreatures = new ArrayList<ApexCreature>();
	
	private ArrayList<SuperPredator> superPredators = new ArrayList<SuperPredator>();
	
	private ArrayList<SmallPredator> smallPredators = new ArrayList<SmallPredator>();
	
	private ArrayList<Herbivore> herbivores = new ArrayList<Herbivore>();
	
	private ArrayList<Resource> resources = new ArrayList<Resource>();

	public Ecosystem(){
		
		for (int i=1; i<=3; i++){
			
			Resource resource = new Resource(i);
			
			consumables.put(resource.getId(), resource);
			
			resources.add(resource);
			
		}
		
		for (int i=1; i<=7; i++){
			
			Herbivore herbivore = new Herbivore(i, GlobalConstants.B_GBUF);
			
			consumables.put(herbivore.getId(), herbivore);
			
			herbivores.add(herbivore);
			
		}
		
		for (int i=1; i<=15; i++){
			
			SmallPredator smallPredator = new SmallPredator(i, GlobalConstants.C_GBUF);
			
			consumables.put(smallPredator.getId(), smallPredator);
			
			smallPredators.add(smallPredator);
			
		}
		
		for (int i=1; i<=7; i++){
			
			SuperPredator superPredator = new SuperPredator(i, GlobalConstants.D_GBUF);
			
			consumables.put(superPredator.getId(), superPredator);
			
			superPredators.add(superPredator);
			
		}
		
		for (int i=1; i<=2; i++){
			
			ApexCreature apex = new ApexCreature(i, GlobalConstants.E_GBUF);
			
			consumables.put(apex.getId(), apex);
			
			apexCreatures.add(apex);
			
		}
		
		for (Herbivore herbivore: herbivores){
			
			herbivore.setDiet(Nature.whatToEat(this, herbivore));
			
			herbivore.setConsumers(Nature.whoPreysOnThis(this, herbivore));
			
		}
		
		for (SmallPredator smallPredator: smallPredators){
			
			smallPredator.setDiet(Nature.whatToEat(this, smallPredator));
			
			smallPredator.setConsumers(Nature.whoPreysOnThis(this, smallPredator));
			
		}
		
		for (SuperPredator superPredator: superPredators){
			
			superPredator.setDiet(Nature.whatToEat(this, superPredator));
			
			superPredator.setConsumers(Nature.whoPreysOnThis(this, superPredator));
			
		}
		
		for (ApexCreature apex: apexCreatures){
			
			apex.setDiet(Nature.whatToEat(this, apex));
			
		}		
		
	}
	
	public Consumable getConsumable(String id){
		
		return this.consumables.get(id);
		
	}
	
}
