package com.revature.enums;

public enum AccountType {
	
	CHECKING(0),
	SAVINGS(1);
	
	private int type;
	
	AccountType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
}
