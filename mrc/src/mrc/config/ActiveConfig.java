package mrc.config;

import java.util.ArrayList;
import java.util.List;

public class ActiveConfig {

	private static List<Integer> populations;
	
	private static List<Float> growthThresholds;
	
	public static void initialize(){
		
		populations = new ArrayList<Integer>();
		
		growthThresholds = new ArrayList<Float>();
		
		for (int i=0; i<Global.NEW_SPAWN_STATUSES.length; i++){
			
			Integer pop = new Integer(Integer.parseInt(Global.NEW_SPAWN_STATUSES[i]));
			
			populations.add(pop);
			
		}
		
		for (int i=0; i<Global.GROWTH_THRESHOLDS.length; i++){
			
			Float threshold = new Float(Float.parseFloat(Global.GROWTH_THRESHOLDS[i]));
			
			growthThresholds.add(threshold);
			
		}
		
	}
	
	public static int getPopStatus(int lvl){
		
		return populations.get(lvl).intValue();
		
	}
	
	public static float getGrowthThreshold(int lvl){
		
		return growthThresholds.get(lvl).floatValue();
		
	}

	public static List<Integer> getPopulations() {
		
		return populations;
		
	}

	public static void setPopulations(List<Integer> populations) {
		
		ActiveConfig.populations = populations;
		
	}

	public static List<Float> getGrowthThresholds() {
		
		return growthThresholds;
		
	}

	public static void setGrowthThresholds(List<Float> growthThresholds) {
		
		ActiveConfig.growthThresholds = growthThresholds;
		
	}	
	
}
