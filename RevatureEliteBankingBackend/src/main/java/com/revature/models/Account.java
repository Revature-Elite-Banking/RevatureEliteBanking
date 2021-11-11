package com.revature.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.enums.AccountType;

@Entity //Mapping the Class as a DB entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int id;
	
	private Date creationTime;
	//There are no deposit or withdrawal methods yet
	//Since this app doesn't connect to any payment services, account creation is done by entering a starting balance in a text field
	//If you want you can make a new checking account with 9999999999999999 imaginary dollars
	private double balance;
	
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "user_id")
	private User user;
	
	//As of now the only account types are checking and savings
	private AccountType type;
	
	@OneToMany(mappedBy="account", cascade = CascadeType.ALL)
	@JsonIgnore
	//At the very bottom of the boilerplate code there's a method to add to this list
	//All new accounts start with a deposit equal to their starting balance
    private List<Transaction> transactions;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int id, Date creationTime, double balance, User user, AccountType type,
			List<Transaction> transactions) {
		super();
		this.id = id;
		this.creationTime = creationTime;
		this.balance = balance;
		this.user = user;
		this.type = type;
		this.transactions = transactions;
	}

	public Account(Date creationTime, double balance, User user, AccountType type,
			List<Transaction> transactions) {
		super();
		this.creationTime = creationTime;
		this.balance = balance;
		this.user = user;
		this.type = type;
		this.transactions = transactions;
	}

	public Account(double balance, AccountType type) {
		super();
		this.balance = balance;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", creationTime=" + creationTime + ", balance=" + balance + ", user=" + user
				+ ", type=" + type + ", transactions=" + transactions.size() + "]";
	}

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
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void addTransactions(Transaction tran) {
		this.transactions.add(tran);
	}

	
}
