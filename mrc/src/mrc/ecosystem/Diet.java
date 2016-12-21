package mrc.ecosystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Diet {

	private HashMap<String, Share> shares;
	
	private int appetite;
	
	private float totalNormalized;
	
	public Diet(int appetite, int predatorStatus){
		
		this.appetite = appetite;
		
		this.shares = new HashMap<String, Share>();
		
	}
	
	public void addShare(String preyId, float shareValue){
		
		Share share = new Share();
		
		share.setValue(shareValue);
		
		shares.put(preyId, share);
		
	}
	
	public void normalizeShares(){
		
		float totalShareValue = 0;
		
		Iterator<String> iter = shares.keySet().iterator();
		
		ArrayList<Share> sharesList = new ArrayList<Share>();
		
		while (iter.hasNext()){
			
			Share share = shares.get(iter.next());
			
			totalShareValue += share.getValue();
			
			sharesList.add(share);
			
		}
		
		for (Share share: sharesList){
			
			float normalizedShare = share.getValue() * (share.getValue() / totalShareValue);
			
			share.setValue(normalizedShare);
			
			this.totalNormalized += normalizedShare;
			
		}
		
	}	

	public float getTotalNormalized() {
		
		return totalNormalized;
		
	}

	public float getShareValue(String preyId){
		
		if (shares.containsKey(preyId))
					
			return shares.get(preyId).getValue();
		
		else
			
			return 0;
		
	}
	
	public void resolveHuntIntensities(int predatorStatus){
		
		//
		
	}
	
	public int getAppetite() {
		
		return appetite;
		
	}

	public void setAppetite(int appetite) {
		
		this.appetite = appetite;
		
	}

	public class Share implements Comparable<Share>{
		
		// preference: 1-3
		private int huntIntensity;
		
		private float value;
		
		private boolean normalized;
		
		public int getHuntIntensity() {
			
			return huntIntensity;
			
		}

		public void setHuntIntensity(int huntIntensity) {
			
			this.huntIntensity = huntIntensity;
			
		}

		public float getValue() {
			
			return this.value;
			
		}

		public void setValue(float value) {
			
			this.value = value;
			
		}		

		public boolean isNormalized() {
			
			return normalized;
			
		}

		public void setNormalized(boolean normalized) {
			
			this.normalized = normalized;
			
		}

		@Override
		public int compareTo(Share other) {
			
			if (this.value > other.getValue())
				
				return 1;
				
			else
				
				return -1;
			
		}
		
	}
	
}
