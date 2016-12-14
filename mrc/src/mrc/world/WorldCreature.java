package mrc.world;

public class WorldCreature implements Comparable<WorldCreature> {

	private String id;
	
	private int population;
	
	private int rarity;
	
	private int rarbonus;
	
	private int trend;
	
	private int cost;
	
	private int gain;
	
	public WorldCreature(String id){
		
		this.id = id;
		
	}

	public String getId() {
		
		return id;
		
	}

	public int getPopulation() {
		
		return population;
		
	}

	public void setPopulation(int population) {
		
		this.population = population;
		
	}

	public int getRarity() {
		
		return rarity;
		
	}

	public void setRarity(int rarity) {
		
		this.rarity = rarity;
		
	}	

	public int getRarbonus() {
		
		return rarbonus;
		
	}

	public void setRarbonus(int rarbonus) {
		
		this.rarbonus = rarbonus;
		
	}

	public int getTrend() {
		
		return trend;
		
	}

	public void setTrend(int trend) {
		
		this.trend = trend;
		
	}

	public int getCost() {
		
		return cost;
		
	}

	public void setCost(int cost) {
		
		this.cost = cost;
		
	}

	public int getGain() {
		
		return gain;
		
	}

	public void setGain(int gain) {
		
		this.gain = gain;
		
	}

	@Override
	public int compareTo(WorldCreature other) {
		
		return other.getPopulation() - this.getPopulation();
		
	}
	
	@Override
	public boolean equals(Object other){
		
		if (other instanceof WorldCreature){
			
			return this.getId().equals(((WorldCreature)other).getId());
			
		} else {
			
			return false;
			
		}
		
	}
	
}