package mrc.ecosystem;

public interface Countable {

	public int getTotal();
	
	public int getShift();
	
	public void eaten(int eatenThisStep);
	
	public int getEatenThisStep();
	
	public int getDueShare(Population predator);
	
	public int getCompetitorsForThis();
	
}
