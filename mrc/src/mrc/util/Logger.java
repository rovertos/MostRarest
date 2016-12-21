package mrc.util;
import mrc.config.Global;
import mrc.ecosystem.Countable;
import mrc.geography.Area;
import mrc.geography.Location;

public class Logger {
	
	private int tabLength = 8;
	
	public String getPrettyPrint(Area area, Location location){
		
		if (area.getPopulationMap().containsKey(location)){
		
			Countable countable = area.getPopulationMap().get(location);
			
			String areatrc = countable.getId() + ":" + countable.getStatus()+ "(" + Global.formatter.format(countable.getGrowthFactor()) + "," + Global.formatter.format(countable.getPerishFactor()) + ")";
			
			return getPrettyPrint(areatrc);
		
		} else {
			
			return getPrettyPrint("");
			
		}
		
	}
	
	public String getPrettyPrint(int i){
		
		return getPrettyPrint("" + i);
		
	}
	
	public String getPrettyPrint(String str){
		
		String tab = "";
		
		for (int i=0; i<tabLength - str.length(); i++){
			
			tab += " ";
			
		}
		
		return tab + str;		
		
	}

	public int getTabLength() {
		
		return tabLength;
		
	}

	public void setTabLength(int tabLength) {
		
		this.tabLength = tabLength;
		
	}
	
	public String getSeparator(String str1, String str2, int repeat){
		
		StringBuffer sep = new StringBuffer();
		
		boolean use2 = false;
		
		for (int i=0; i<repeat; i++){
			
			if (use2) {
				
				sep.append(getPrettyTab(str2));
				
				use2 = false;
				
			} else {
				
				sep.append(getPrettyTab(str1));
				
				use2 = true;
				
			}
			
		}
		
		return sep.toString();
		
	}
	
	public String getPrettyTab(String str){
		
		StringBuffer buf = new StringBuffer();
		
		for (int i=0; i<this.tabLength; i++){
			
			buf.append(str.substring(0, 1));
			
		}
		
		return buf.toString();
		
	}
	
}
