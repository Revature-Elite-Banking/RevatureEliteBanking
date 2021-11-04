package com.revature.models;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


public class Notification {

	private enum NotificationType {

	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private int id;
	
	@CreationTimestamp
	@Column(name = "createdOn")
    private Date createdOn;
	
	@Column(name = "recipient_id")
	private int recipient_id;
	
	@Column(name = "related_transaction_id")
	private int related_transaction_id;
    
    @Column(name = "type")
    private NotificationType type;

    @Column(name = "Description")
    private String Description;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + id;
		result = prime * result + recipient_id;
		result = prime * result + related_transaction_id;
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
		Notification other = (Notification) obj;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (id != other.id)
			return false;
		if (recipient_id != other.recipient_id)
			return false;
		if (related_transaction_id != other.related_transaction_id)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", createdOn=" + createdOn + ", recipient_id=" + recipient_id
				+ ", related_transaction_id=" + related_transaction_id + ", type=" + type + ", Description="
				+ Description + "]";
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification(int recipient_id, int related_transaction_id, NotificationType type, String description) {
		super();
		this.createdOn = new Date(System.currentTimeMillis());
		this.recipient_id = recipient_id;
		this.related_transaction_id = related_transaction_id;
		this.type = type;
		Description = description;
	}
    
    
    
    
    
	
}
