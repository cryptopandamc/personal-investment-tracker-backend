package com.junemc.PersonalInvestmentTracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="account_gen")
	@SequenceGenerator(name="account_gen", sequenceName="ACCOUNT_SEQ", allocationSize=1)
	private long accountId;
	
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;
	
	@Column
	private String accountAlias;
	
	@Column
	private double balance;

	public Account(double balance) {
		super();
		this.balance = balance;
	}
	
	

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}



	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

	public long getAccountId() {
		return accountId;
	}



	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getAccountAlias() {
		return accountAlias;
	}



	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		return true;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + "]";
	}
	
	

}
