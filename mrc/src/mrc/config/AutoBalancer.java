package mrc.config;

import java.util.List;

import mrc.ecosystem.Population;
import mrc.world.TheWild;

public class AutoBalancer {

	private static int MAX_STEPS = 10000;
	
	private static int TRIES = 0;
	
	private static int STEPS = 0;
	
	private static boolean PROCEED = true;
	
	private static MrcConfig best;
	
	private static String message;
	
	public static void main(String[] args) {
		
		ActiveConfig.initialize();
		
		best = new MrcConfig();
		
		best.copyCurrentActiveConfig();
		
		best.setSteps(AutoBalancer.STEPS);
		
		AutoBalancer.start();
		
	}
	
	public static void start(){
		
		while (AutoBalancer.TRIES < 10){
			
			TheWild theWild = new TheWild();
			
			theWild.genesis();
			
			while (AutoBalancer.PROCEED && AutoBalancer.STEPS < MAX_STEPS){
				
				AutoBalancer.STEPS++;
				
				theWild.executeStep();
				
			}
			
			if (AutoBalancer.STEPS > best.getSteps()){
				
				best = new MrcConfig();
				
				best.copyCurrentActiveConfig();
				
				best.setSteps(AutoBalancer.STEPS);
				
				best.setMessage(AutoBalancer.message);
				
				System.out.println("Found new Best:");
				
				AutoBalancer.printBestConfig();
							
			}			
			
			if (AutoBalancer.STEPS >= MAX_STEPS){
				
				best.setMessage("COMPLETED " + MAX_STEPS + " with no extinctions or overgrowths!");
				
				break;
				
			}
			
			AutoBalancer.PROCEED = true;
			
			AutoBalancer.TRIES++;
			
		}
		
		System.out.println("***************************************");
		System.out.println("** Completed " + AutoBalancer.TRIES + " tries");
		System.out.println("");
		
		System.out.println("Best is..");
		
		AutoBalancer.printBestConfig();
		
		
	}
	
	public static void printBestConfig(){
		
		List<Integer> bestPops = best.getPopulations();
		
		List<Float> growthThresholds = best.getGrowthThresholds();
		
		System.out.print("Starting Populations: {");		
		for (int i=0; i<bestPops.size(); i++){			
			System.out.print("{\"" + bestPops.get(i).intValue() + "\"}");			
			if (i+1 < bestPops.size())
				System.out.print(",");
		}		
		System.out.println("};");
		
		System.out.print("Starting GThresholds: {");		
		for (int i=0; i<growthThresholds.size(); i++){			
			System.out.print("{\"" + (int)growthThresholds.get(i).floatValue() + "\"}");			
			if (i+1 < growthThresholds.size())
				System.out.print(",");
		}		
		System.out.println("};");
		
		if (best.getMessage() != null)
			System.out.println(best.getMessage());
		
	}
	
	public static void reportExtinction(Population population){
		
		AutoBalancer.PROCEED = false;
		
		AutoBalancer.message = population.getSpecies().getId() + " became EXTINCT after " +  AutoBalancer.STEPS + " steps...";
		
		System.out.print(AutoBalancer.message);
		
		int lvl = population.getSpecies().getLevel();
		
		if (lvl+1<ActiveConfig.getPopulations().size())
			
			AutoBalancer.readjustConfigPopulation(lvl+1, -1);		
		
		AutoBalancer.readjustConfigPopulation(lvl, 2);
		
		AutoBalancer.readjustConfigPopulation(lvl-1, 1);
		
		System.out.println("");	
		
	}
	
	public static void reportOvergrowth(Population population){
		
		AutoBalancer.PROCEED = false;
		
		AutoBalancer.message = population.getSpecies().getId() + " became OVERGROWN at " + population.getStatus() + " after " +  AutoBalancer.STEPS + " steps: ";
		
		System.out.print(AutoBalancer.message);
		
		int lvl = population.getSpecies().getLevel();
		
		if (lvl+1<ActiveConfig.getPopulations().size())
			
			AutoBalancer.readjustConfigPopulation(lvl+1, 1);
		
		AutoBalancer.readjustConfigPopulation(lvl, -2);
		
		AutoBalancer.readjustConfigPopulation(lvl-1, -1);
		
		System.out.println("");
		
	}	
	
	private static void readjustConfigPopulation(int index, int add){
		
		Integer pop = ActiveConfig.getPopulations().remove(index);
		
		Integer newPop = new Integer(pop.intValue() + add);
		
		if (newPop.intValue() > 0){
		
			System.out.print("lvl " + index + " " + pop.intValue() + "->" + newPop.intValue() + ". ");
			
			ActiveConfig.getPopulations().add(index, newPop);
		
		} else {
			
			ActiveConfig.getPopulations().add(index, pop);
			
		}
		
	}
	
}
