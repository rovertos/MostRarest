package mrc.player;

import java.util.List;

public class Order {

	private String playerName;
	
	private List<Transaction> transactions;	
	
	public String getPlayerName() {
		
		return playerName;
		
	}

	public void setPlayerName(String playerName) {
		
		this.playerName = playerName;
		
	}	
	
	public List<Transaction> getTransactions() {
		
		return transactions;
		
	}

	public void setTransactions(List<Transaction> transactions) {
		
		this.transactions = transactions;
		
	}

	public class Transaction {
		
		
		
	}
	
}
