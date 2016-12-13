package mrc.json;

import java.io.Serializable;

public class CreatureJSON implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private int population;
	
	private int popShift;
	
	private int netGain;
	
	private int netLoss;
	
	private int stepsToRampant;
	
	private int stepsToEndangered;
	
	private int rarity;
	
	private int newOwners;
	
	private int cost;	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getPopShift() {
		return popShift;
	}

	public void setPopShift(int popShift) {
		this.popShift = popShift;
	}

	public int getNetGain() {
		return netGain;
	}

	public void setNetGain(int netGain) {
		this.netGain = netGain;
	}

	public int getNetLoss() {
		return netLoss;
	}

	public void setNetLoss(int netLoss) {
		this.netLoss = netLoss;
	}

	public int getStepsToRampant() {
		return stepsToRampant;
	}

	public void setStepsToRampant(int stepsToRampant) {
		this.stepsToRampant = stepsToRampant;
	}

	public int getStepsToEndangered() {
		return stepsToEndangered;
	}

	public void setStepsToEndangered(int stepsToEndangered) {
		this.stepsToEndangered = stepsToEndangered;
	}

	public int getRarity() {
		return rarity;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}	

	public int getNewOwners() {
		return newOwners;
	}

	public void setNewOwners(int newOwners) {
		this.newOwners = newOwners;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}	

}
