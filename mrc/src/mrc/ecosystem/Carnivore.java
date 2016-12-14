package mrc.ecosystem;

import mrc.config.Logger;

public class Carnivore extends Species {

	public Carnivore(String category, int index, int gbuf, float gmult) {
		
		super(category, index, gbuf, gmult);
		
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
