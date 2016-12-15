package mrc.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import mrc.ecosystem.Ecosystem;
import mrc.ecosystem.Species;

public class Nature {
	
	private static HashMap<String, String> diets = new HashMap<String,String>();
	
	private static HashMap<String, String> predators = new HashMap<String,String>();
	
	static {
		diets.put("b1","a1");
		diets.put("b2","a1");
		diets.put("b3","a1,a2");
		diets.put("b4","a2");
		diets.put("b5","a2,a3");
		diets.put("b6","a3");
		diets.put("b7","a3");
		diets.put("c1","b1");
		diets.put("c2","b1");
		diets.put("c3","b1,b2");
		diets.put("c4","b2");
		diets.put("c5","b2,b3");
		diets.put("c6","b3");
		diets.put("c7","b3,b4");
		diets.put("c8","b4");
		diets.put("c9","b4,b5");
		diets.put("c10","b5");
		diets.put("c11","b5,b6");
		diets.put("c12","b6");
		diets.put("c13","b6,b7");
		diets.put("c14","b7");
		diets.put("c15","b7");
		diets.put("d1","c1,c2,c3");
		diets.put("d2","c3,c4,c5");
		diets.put("d3","c3,c4,c5");
		diets.put("d4","c5,c6,c7");
		diets.put("d5","c7,c8,c9");
		diets.put("d6","c9,c10,c11");
		diets.put("d7","c11,c12,c13");
		diets.put("e1","d1,d2,d3");
		diets.put("e2","d5,d6,d7");
		
		Iterator<String> iter = diets.keySet().iterator();
		
		while (iter.hasNext()){
			
			String predator = iter.next();
			
			String[] prey = diets.get(predator).split(",");
			
			for (int i=0; i<prey.length; i++){
				
				if (predators.containsKey(prey[i])){
					
					predators.put(prey[i], predators.get(prey) + "," + predator);
					
				} else {
					
					predators.put(prey[i], predator);
					
				}
				
			}
			
		}
		
	}	
	
}
