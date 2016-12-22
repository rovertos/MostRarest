package mrc.ecosystem;

import java.util.Iterator;

import mrc.config.ActiveConfig;
import mrc.config.AutoBalancer;
import mrc.config.Global;
import mrc.config.MrcConfig;
import mrc.ecosystem.Diet.Share;
import mrc.geography.Area;
import mrc.geography.Location;
import mrc.world.FantasyWildlifeFund;

public class Population extends Countable {
	
	private Species species;
	
	private Diet diet;
		
	public Population(Species species, int status, Area area, Location location, float growthThreshold, int carryingCapacityFactor){
		
		super(status, area, location, growthThreshold, carryingCapacityFactor);
		
		this.species = species;
		
		FantasyWildlifeFund.register(this);
		
	}

	public void executeStep() {
		
		// TODO: STARVATION!		

		float growthFactor = this.getGrowthFactor();
		
		float perishFactor = this.getPerishFactor();
		
		int oldStatus = this.status;

		growthThisStep = (growthFactor - status + perishFactor);

		accumulatedGrowth += growthThisStep;
		
		if (Math.abs(accumulatedGrowth) > growthThreshold) {
			
			// AUTOBALANCING START...
			
			if (accumulatedGrowth > 0 && status > (Integer.parseInt(Global.NEW_SPAWN_STATUSES[this.getSpecies().getLevel()]) * 3)){
				
				AutoBalancer.reportOvergrowth(this);
				
			} else if (accumulatedGrowth < 0 && status == 1){
				
				AutoBalancer.reportExtinction(this);
				
			}
			
			// ...AUTOBALANCING END

			if (accumulatedGrowth > 0 && status < 5) {

				status++;
				
				accumulatedGrowth = accumulatedGrowth - growthThreshold;
				
				FantasyWildlifeFund.register(this);

			} else if (accumulatedGrowth < 0 && status > 1) {
				
				status--;
				
				accumulatedGrowth = growthThreshold + accumulatedGrowth;
				
				FantasyWildlifeFund.register(this);
				
			}
			
		}

	}
	
	public Diet getDiet() {
		
		return diet;
		
	}

	public float getGrowthFactor(){
		
		// TODO: appetite
		
		diet = new Diet(5, this.status);
		
		for (Countable prey: this.area.getDiet(this)){
			
			float dueShare = prey.giveDueShare(this);
			
			diet.addShare(prey.getId(), dueShare, false);
			
		}
		
		diet.normalizeShares();
		
		float totalNormalized = diet.getTotalNormalized();
		
		return totalNormalized;
				
	}
	
	public float giveClaimForShare(Countable askingPrey){
		
		float dueSharesFromOthers = 0;
		
		for (Countable prey: this.area.getDiet(this)){
			
			if (!prey.getId().equals(askingPrey.getId()))
			
				dueSharesFromOthers += prey.giveDueShare(this);
			
		}
		
		float claimedShare = (float)this.status * ((float)askingPrey.status / ((float)askingPrey.status + dueSharesFromOthers));
		
		return claimedShare;
		
	}
	
	public String getGrowthLog(){
		
		StringBuffer buf = new StringBuffer();
		
		Iterator<String> iter = diet.getShares().keySet().iterator();
		
		while(iter.hasNext()){
			
			String preyId = iter.next();
			
			Share share = diet.getShares().get(preyId);
			
			buf.append(preyId + ">" + Global.formatter.format(share.getValue()) + ",");
			
		}
		
		String log = buf.length() > 1 ? buf.substring(0, buf.length()-1) : ".";
		
		return log;
		
	}	
	
	public String getId(){
		
		return this.species.getId();
		
	}
	
	public Species getSpecies() {
		
		return species;
		
	}
	
}
