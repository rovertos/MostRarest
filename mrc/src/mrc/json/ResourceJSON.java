package mrc.json;

import java.io.Serializable;

public class ResourceJSON implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private int amount;
	
	private int amountShift;	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmountShift() {
		return amountShift;
	}

	public void setAmountShift(int amountShift) {
		this.amountShift = amountShift;
	}	
	
}
