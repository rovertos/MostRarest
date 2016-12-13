package mrc.json;

import java.io.Serializable;
import java.util.List;

public class EcosystemJSON implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean endTurn;
	
	private int money;
	
	private List<CreatureJSON> creatures;
	
	private List<ResourceJSON> resources;

	public boolean isEndTurn() {
		return endTurn;
	}

	public void setEndTurn(boolean endTurn) {
		this.endTurn = endTurn;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public List<CreatureJSON> getCreatures() {
		return creatures;
	}

	public void setCreatures(List<CreatureJSON> creatures) {
		this.creatures = creatures;
	}

	public List<ResourceJSON> getResources() {
		return resources;
	}

	public void setResources(List<ResourceJSON> resources) {
		this.resources = resources;
	}	

}
