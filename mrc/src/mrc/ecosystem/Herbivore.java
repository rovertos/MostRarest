package mrc.ecosystem;

import mrc.config.Logger;

public class Herbivore extends Species {
	
	public static String category = "b";

	public Herbivore(int index, int gbuf, float gmult, int rarbonus) {
		
		super(category, index, gbuf, gmult, rarbonus);
		
	}
	
	public void executeStep(Ecosystem ecosystem){
		
		if (this.getAvailable() > 0){
			
			// CALCULATE & ADD GROWTH SHIFT
			
			float growthShift = (this.getGmult() * this.getFoodPlethora()) - this.getPredatorBurden();
			
			this.addGrowth(growthShift);
			
			// SHIFT POPULATION
			
			if (this.getGrowth() > this.getGbuf()){
				
				this.increase(this.getGrowth() - this.getGbuf());
				
			} else if (this.getGrowth() < - this.getGbuf()) {
				
				this.decrease(this.getGrowth() + this.getGbuf());
				
			}
			
			Logger.logStep(this, "");
		
		}
		
	}	
	
}
