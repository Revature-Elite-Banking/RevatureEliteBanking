package com.revature.enums;

public enum TransactionStatus {
	PENDING(0),
	COMPLETED(1);
	
	private int status;
	
	TransactionStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
}
