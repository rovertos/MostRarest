package mrc.config;

import java.util.ArrayList;
import java.util.List;

public class MrcConfig {

	private int steps;
	
	private List<Integer> populations;
	
	private List<Float> growthThresholds;
	
	private String message;
	
	public void copyCurrentActiveConfig(){
		
		populations = new ArrayList<Integer>();
		
		growthThresholds = new ArrayList<Float>();
		
		for (int i=0; i<ActiveConfig.getPopulations().size(); i++){
			
			Integer pop = new Integer(ActiveConfig.getPopulations().get(i).intValue());
			
			populations.add(pop);
			
		}
		
		for (int i=0; i<ActiveConfig.getGrowthThresholds().size(); i++){
			
			Float growthThreshold = new Float(ActiveConfig.getGrowthThresholds().get(i).floatValue());
			
			growthThresholds.add(growthThreshold);
			
		}
		
	}

	public int getSteps() {
		
		return steps;
		
	}

	public void setSteps(int steps) {
		
		this.steps = steps;
		
	}

	public List<Integer> getPopulations() {
		
		return populations;
		
	}

	public void setPopulations(List<Integer> populations) {
		
		this.populations = populations;
		
	}

	public List<Float> getGrowthThresholds() {
		
		return growthThresholds;
		
	}

	public void setGrowthThresholds(List<Float> growthThresholds) {
		
		this.growthThresholds = growthThresholds;
		
	}

	public String getMessage() {
		
		return message;
		
	}

	public void setMessage(String message) {
		
		this.message = message;
		
	}	
	
}
