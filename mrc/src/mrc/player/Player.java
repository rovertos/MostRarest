package mrc.player;

import mrc.ecosystem.FantasyPark;

public class Player {

	private String name;
	
	private FantasyPark fantasyPark;
	
	private BankAccount bankAccount;

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}

	public FantasyPark getFantasyPark() {
		
		return fantasyPark;
		
	}

	public void setFantasyPark(FantasyPark fantasyPark) {
		
		this.fantasyPark = fantasyPark;
		
	}

	public BankAccount getBankAccount() {
		
		return bankAccount;
		
	}

	public void setBankAccount(BankAccount bankAccount) {
		
		this.bankAccount = bankAccount;
		
	}
	
	
	
}
