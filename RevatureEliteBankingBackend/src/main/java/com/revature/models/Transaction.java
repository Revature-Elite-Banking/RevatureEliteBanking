package com.revature.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.enums.TransactionStatus;
import com.revature.enums.TransactionType;

@Repository
@Entity // map class to a database entity
@Table(name="transactions") // name the table 'transactions' default would be Transaction
public class Transaction {
	
	@Id // makes it the primary key of the table
	@GeneratedValue(strategy= GenerationType.IDENTITY) // makes it the serial datatype
	private int id;
	
	// we'll let hibernate handle these
	private double amount;
	
	@Column(name="transaction_type")
	private TransactionType type;
	
	@Column(name="date_posted")
	private Date date;
	
	private TransactionStatus status;
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account_id", nullable=false)
	private Account account;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Status is included in this class for future groups
	//The group who made this project won't connect to any payment services, and status would only matter for groups who would
	public Transaction(int id, double amount, TransactionType type, Date date, TransactionStatus status,
			String description, Account account) {
		super();
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.date = date;
		this.status = status;
		this.description = description;
		this.account = account;
	}

	public Transaction(double amount, TransactionType type, Date date, TransactionStatus status, String description,
			Account account) {
		super();
		this.amount = amount;
		this.type = type;
		this.date = date;
		this.status = status;
		this.description = description;
		this.account = account;
	}

	public Transaction(double amount, TransactionType type, Date date, String description, Account account) {
		super();
		this.amount = amount;
		this.type = type;
		this.date = date;
		this.description = description;
		this.account = account;
	}

	public Transaction(double amount, TransactionType type, String description) {
		super();
		this.amount = amount;
		this.type = type;
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", type=" + type + ", date=" + date + ", status="
				+ status + ", description=" + description + ", account=" + account + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public TransactionType getType() {
		return type;
	}


	public void setType(TransactionType type) {
		this.type = type;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public TransactionStatus getStatus() {
		return status;
	}


	public void setStatus(TransactionStatus status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	
}
