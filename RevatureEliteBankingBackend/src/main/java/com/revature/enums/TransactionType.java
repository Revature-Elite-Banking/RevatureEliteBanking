package com.revature.enums;

public enum TransactionType {

	DEPOSIT(0),
	WITHDRAWL(1),
	TRANSFER(2);
	
	private int type;
	
	TransactionType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
}
