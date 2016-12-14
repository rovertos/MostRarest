package mrc.ecosystem;

public class Resource extends Consumable {

	public static String category = "a";	
	
	private int index;
	
	private float growthShift;
	
	private int trend;	
	
	public Resource(int index){
		
		this.index = index;
		
	}
	
	public String getId() {

		return category + this.index;
		
	}	
	
	public float getGrowthShift() {
		
		return growthShift;
		
	}
	
	public void increase(float growthOverload){
		
		this.trend = 1;
		
		this.setAvailable(this.getAvailable() + 1);
		
		this.setGrowth(growthOverload);
		
	}
	
	public void decrease(float growthOverload){
		
		this.trend = -1;
		
		this.setAvailable(this.getAvailable() - 1);
		
		this.setGrowth(-growthOverload);
		
	}
	
	public int getTrend() {
		
		return trend;
		
	}	

	public void setGrowthShift(float growthShift) {
		
		this.growthShift = growthShift;
		
	}

	public void executeStepA(Ecosystem ecosystem){
		
		// CALCULATE GROWTH SHIFT
		
		float growthShift = - this.getPredatorBurden();
		
		this.setGrowthShift(growthShift);
		
	}
	
	public void executeStepB(Ecosystem ecosystem, float heal){
		
		// ADD GROWTH SHIFT WITH HEAL
		
		this.addGrowth(this.getGrowthShift() + heal);
		
		// SHIFT AVAILABLE AMOUNT
		
		if (this.getGrowth() > this.getGbuf()){
			
			this.increase(this.getGrowth() - this.getGbuf());
			
		} else if (this.getGrowth() < - this.getGbuf()) {
			
			this.decrease(this.getGrowth() + this.getGbuf());
			
		}		
		
	}	
	
}
