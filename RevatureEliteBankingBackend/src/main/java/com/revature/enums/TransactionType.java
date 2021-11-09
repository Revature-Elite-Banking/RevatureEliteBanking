package com.revature.enums;

public enum TransactionType {

	DEPOSIT(0),
	WITHDRAWL(1),
	TRANSFERIN(2),
	TRANSFEROUT(3);
	
	private int type;
	
	TransactionType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
}
