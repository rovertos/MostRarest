package mrc.ecosystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Diet {

	private List<Prey> preys;
	
	private int appetite;
	
	public Diet(int appetite, int predatorStatus){
		
		this.appetite = appetite;
		
		this.preys = new ArrayList<Prey>();
		
	}
	
	public void addPrey(Countable countable, float dueShare){
		
		Prey prey = new Prey();
		
		prey.setCountable(countable);
		
		prey.setDueShare(dueShare);
		
	}
	
	public void resolveHuntIntensities(int predatorStatus){
		
		Collections.sort(preys);
		
		for (Prey prey: preys){
			
			
			
		}
		
	}
	
	public int getAppetite() {
		
		return appetite;
		
	}

	public void setAppetite(int appetite) {
		
		this.appetite = appetite;
		
	}

	public class Prey implements Comparable<Prey>{
		
		// preference: 1-3
		private int huntIntensity;
		
		private float dueShare;
		
		private Countable countable;
		
		public Countable getCountable() {
			
			return countable;
			
		}

		public void setCountable(Countable countable) {
			
			this.countable = countable;
			
		}

		public int getHuntIntensity() {
			
			return huntIntensity;
			
		}

		public void setHuntIntensity(int huntIntensity) {
			
			this.huntIntensity = huntIntensity;
			
		}

		public float getDueShare() {
			
			return this.dueShare;
			
		}

		public void setDueShare(float dueShare) {
			
			this.dueShare = dueShare;
			
		}

		@Override
		public int compareTo(Prey other) {
			
			if ((this.dueShare * (float)this.getCountable().getStatus()) >= (other.getDueShare() * (float)other.getCountable().getStatus()))
				
				return 1;
				
			else
				
				return -1;
			
		}
		
	}
	
}
