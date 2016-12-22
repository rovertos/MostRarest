package mrc.util;
import java.util.List;

import mrc.config.Global;
import mrc.ecosystem.Countable;
import mrc.ecosystem.Ecosystem;
import mrc.geography.Area;
import mrc.geography.Location;

public class Logger {
	
	private int tabLength = 8;
	
	public String getPrettyPrint1(Area area, Location location){
		
		if (area.getPopulationMap().containsKey(location)){
		
			Countable countable = area.getPopulationMap().get(location);
			
			String areatrc = countable.getId() + ":" + countable.getStatus()+ "(" + Global.formatter.format(countable.getGrowthThisStep()) + ")|";
			
			return getPrettyPrint(areatrc);
		
		} else {
			
			return getPrettyPrint("");
			
		}
		
	}
	
	public String getPrettyPrint2(Area area, Location location){
		
		if (area.getPopulationMap().containsKey(location)){
		
			Countable countable = area.getPopulationMap().get(location);
			
			String areatrc = countable.getGrowthLog() + "-" + countable.getPerishLog() + "¦";
			
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
	
	public void print(Ecosystem system){
		
		List<Area> areas = system.getAreas();
		
		String separator = this.getSeparator(" ", "-", 23);
		
		System.out.println(separator);
		
		System.out.println("");
		
		StringBuffer buf = new StringBuffer();
		
		
		
		buf.append(this.getPrettyPrint("OVER:"));
		
		for (int i=0; i<system.getAreas().size(); i++){
			
			buf.append(this.getPrettyPrint(""));
			
			buf.append(this.getPrettyPrint1(areas.get(i), Location.OVER));
			
			buf.append(this.getPrettyPrint(""));
			
		}
		
		buf.append(System.getProperty("line.separator"));
		
/*		buf.append(log.getPrettyPrint(""));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint(""));
			
			buf.append(log.getPrettyPrint2(areas.get(i), Location.OVER));
			
			buf.append(log.getPrettyPrint(""));
			
		}
		
		buf.append(System.getProperty("line.separator"));	*/
		
		
		
		buf.append(this.getPrettyPrint("NORTH:"));
		
		for (int i=0; i<areas.size(); i++){
			
			buf.append(this.getPrettyPrint(""));
			
			buf.append(this.getPrettyPrint1(areas.get(i), Location.NORTH));
			
			buf.append(this.getPrettyPrint(""));
			
		}		
		
		buf.append(System.getProperty("line.separator"));
		
/*		buf.append(log.getPrettyPrint(""));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint(""));
			
			buf.append(log.getPrettyPrint2(areas.get(i), Location.NORTH));
			
			buf.append(log.getPrettyPrint(""));
			
		}		
		
		buf.append(System.getProperty("line.separator"));*/
		
		
		
		buf.append(this.getPrettyPrint("W/IN/E:"));
		
		for (int i=0; i<areas.size(); i++){
			
			buf.append(this.getPrettyPrint1(areas.get(i), Location.WEST));
			
			buf.append(this.getPrettyPrint1(areas.get(i), Location.INNER));
			
			buf.append(this.getPrettyPrint1(areas.get(i), Location.EAST));
			
		}
		
		buf.append(System.getProperty("line.separator"));

/*		buf.append(log.getPrettyPrint(""));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint2(areas.get(i), Location.WEST));
			
			buf.append(log.getPrettyPrint2(areas.get(i), Location.INNER));
			
			buf.append(log.getPrettyPrint2(areas.get(i), Location.EAST));
			
		}
		
		buf.append(System.getProperty("line.separator"));*/		
		
		
		
		buf.append(this.getPrettyPrint("SOUTH:"));
		
		for (int i=0; i<areas.size(); i++){
			
			buf.append(this.getPrettyPrint(""));
			
			buf.append(this.getPrettyPrint1(areas.get(i), Location.SOUTH));
			
			buf.append(this.getPrettyPrint(""));
			
		}
		
		buf.append(System.getProperty("line.separator"));
		
/*		buf.append(log.getPrettyPrint(""));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint(""));
			
			buf.append(log.getPrettyPrint2(areas.get(i), Location.SOUTH));
			
			buf.append(log.getPrettyPrint(""));
			
		}
		
		buf.append(System.getProperty("line.separator"));*/
		
		
		
		buf.append(this.getPrettyPrint("UNDER:"));
		
		for (int i=0; i<areas.size(); i++){
			
			buf.append(this.getPrettyPrint(""));
			
			buf.append(this.getPrettyPrint1(areas.get(i), Location.UNDER));
			
			buf.append(this.getPrettyPrint(""));
			
		}
		
		buf.append(System.getProperty("line.separator"));
		
/*		buf.append(log.getPrettyPrint(""));
		
		for (int i=0; i<this.areas.size(); i++){
			
			buf.append(log.getPrettyPrint(""));
			
			buf.append(log.getPrettyPrint2(areas.get(i), Location.UNDER));
			
			buf.append(log.getPrettyPrint(""));
			
		}*/
		
		
		
		System.out.println(buf.toString());
		
		System.out.println(separator);
		
	}	
	
}
