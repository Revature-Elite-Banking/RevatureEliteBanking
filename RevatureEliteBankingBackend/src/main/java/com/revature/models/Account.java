package com.revature.models;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.revature.enums.AccountType;

@Entity //Mapping the Class as a DB entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int id;
	
	private Timestamp creationTime;
	private double balance;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private int user_id;
	
	private AccountType type;
	
	@OneToMany(mappedBy="account")
    private ArrayList<Transaction> transactions;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + id;
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + user_id;
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
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (id != other.id)
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		if (type != other.type)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", creationTime=" + creationTime + ", balance=" + balance + ", user_id=" + user_id
				+ ", type=" + type + ", transactions=" + transactions + "]";
	}

	public Account(int id, Timestamp creationTime, double balance, int user_id, AccountType type,
			ArrayList<Transaction> transactions) {
		super();
		this.id = id;
		this.creationTime = creationTime;
		this.balance = balance;
		this.user_id = user_id;
		this.type = type;
		this.transactions = transactions;
	}

	public Account(Timestamp creationTime, double balance, int user_id, AccountType type,
			ArrayList<Transaction> transactions) {
		super();
		this.creationTime = creationTime;
		this.balance = balance;
		this.user_id = user_id;
		this.type = type;
		this.transactions = transactions;
	}

	public Account(Timestamp creationTime, double balance, int user_id, AccountType type) {
		super();
		this.creationTime = creationTime;
		this.balance = balance;
		this.user_id = user_id;
		this.type = type;
	}

	public Account() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	

}
