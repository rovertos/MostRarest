package mrc.world;

import java.util.ArrayList;

public class CreatureRarity {

	private int value;
	
	private ArrayList<WorldCreature> worldCreatures = new ArrayList<WorldCreature>();
	
	public int getValue() {
		
		return value;
		
	}

	public void setValue(int value) {
		
		this.value = value;
		
	}

	public ArrayList<WorldCreature> getWorldcreatures() {
		
		return worldCreatures;
		
	}

	public void addWorldcreature(WorldCreature worldCreature) {
		
		this.worldCreatures.add(worldCreature);
		
	}	
	
}
