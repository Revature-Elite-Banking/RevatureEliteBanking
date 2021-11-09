package com.revature.models;

public class TransferDTO {
	int senderID;
	int recipientID;
	double amount;
	
	public TransferDTO(int senderID, int recipientID, double amount) {
		super();
		this.senderID = senderID;
		this.recipientID = recipientID;
		this.amount = amount;
	}

	public TransferDTO() {
		super();
	}

	public int getSenderID() {
		return senderID;
	}

	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}

	public int getRecipientID() {
		return recipientID;
	}

	public void setRecipientID(int recipientID) {
		this.recipientID = recipientID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
