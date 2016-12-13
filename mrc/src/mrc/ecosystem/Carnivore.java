package mrc.ecosystem;

import mrc.config.Logger;

public class Carnivore extends Species {

	public Carnivore(String category, int index, int gbuf) {
		
		super(category, index, gbuf);
		
	}
	
	public void executeStep(Ecosystem ecosystem){
		
		if (this.getAvailable() > 0){
			
			// CALCULATE PREDATOR BURDEN
			
			float predatorBurden = this.getPredatorBurden();
			
			// CALCULATE FOOD PLETHORA
			
			float foodPlethora = this.getFoodPlethora();
			
			// CALCULATE & ADD GROWTH SHIFT
			
			float growthShift = foodPlethora - predatorBurden;
			
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
