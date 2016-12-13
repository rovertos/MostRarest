package mrc.ecosystem;

public class Resource extends Consumable {

	public static String category = "a";	
	
	private int index;
	
	public Resource(int index){
		
		this.index = index;
		
	}
	
	public String getId() {

		return category + this.index;
		
	}
	
}
