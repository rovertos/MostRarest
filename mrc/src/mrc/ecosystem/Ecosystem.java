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
			
			Resource resource = new Resource(i, GlobalConstants.A_GBUF);
			
			consumables.put(resource.getId(), resource);
			
			resources.add(resource);
			
		}
		
		for (int i=1; i<=7; i++){
			
			Herbivore herbivore = new Herbivore(i, GlobalConstants.B_GBUF, GlobalConstants.B_GMULT, GlobalConstants.B_RAR_BONUS);
			
			consumables.put(herbivore.getId(), herbivore);
			
			herbivores.add(herbivore);
			
		}
		
		for (int i=1; i<=15; i++){
			
			SmallPredator smallPredator = new SmallPredator(i, GlobalConstants.C_GBUF, GlobalConstants.C_GMULT, GlobalConstants.C_RAR_BONUS);
			
			consumables.put(smallPredator.getId(), smallPredator);
			
			smallPredators.add(smallPredator);
			
		}
		
		for (int i=1; i<=7; i++){
			
			SuperPredator superPredator = new SuperPredator(i, GlobalConstants.D_GBUF, GlobalConstants.D_GMULT, GlobalConstants.D_RAR_BONUS);
			
			consumables.put(superPredator.getId(), superPredator);
			
			superPredators.add(superPredator);
			
		}
		
		for (int i=1; i<=2; i++){
			
			ApexCreature apex = new ApexCreature(i, GlobalConstants.E_GBUF, GlobalConstants.E_GMULT, GlobalConstants.E_RAR_BONUS);
			
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
	
	public void executeStep(){
		
		// EXECUTE STEP FOR CREATURES
		
		for (int i=0; i<apexCreatures.size(); i++){
			
			apexCreatures.get(i).executeStep(this);
			
		}
		
		for (int i=0; i<superPredators.size(); i++){
			
			superPredators.get(i).executeStep(this);
			
		}
		
		for (int i=0; i<smallPredators.size(); i++){
			
			superPredators.get(i).executeStep(this);
			
		}
		
		for (int i=0; i<herbivores.size(); i++){
			
			superPredators.get(i).executeStep(this);
			
		}
		
		// EXECUTE STEP FOR RESOURCES (WITH HEAL)
		
		float totalGrowthShift = 0;
		
		for (int i=0; i<resources.size(); i++){
			
			resources.get(i).executeStepA(this);
			
			totalGrowthShift += resources.get(i).getGrowthShift();
			
		}
		
		float heal = totalGrowthShift / (float)resources.size();
		
		for (int i=0; i<resources.size(); i++){
			
			resources.get(i).executeStepB(this, heal);
			
		}
		
	}
	
	public Consumable getConsumable(String id){
		
		return this.consumables.get(id);
		
	}
	
}
