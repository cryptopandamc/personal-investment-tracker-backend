package com.junemc.PersonalInvestmentTracker.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
	@SequenceGenerator(name = "account_gen", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
	private long accountId;

	@Column
	private String accountAlias;

	@Column
	private BigDecimal dollarBalance;

	@Column
	private BigDecimal btcBalance;

	public Account(String accountAlias, BigDecimal dollarBalance, BigDecimal btcBalance) {
		super();
		this.accountAlias = accountAlias;
		this.dollarBalance = dollarBalance;
		this.btcBalance = btcBalance;
	}

	public Account() {
		super();
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountAlias() {
		return accountAlias;
	}

	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}

	public BigDecimal getDollarBalance() {
		return dollarBalance;
	}

	public void setDollarBalance(BigDecimal dollarBalance) {
		this.dollarBalance = dollarBalance;
	}

	public BigDecimal getBtcBalance() {
		return btcBalance;
	}

	public void setBtcBalance(BigDecimal btcBalance) {
		this.btcBalance = btcBalance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountAlias == null) ? 0 : accountAlias.hashCode());
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		result = prime * result + ((btcBalance == null) ? 0 : btcBalance.hashCode());
		result = prime * result + ((dollarBalance == null) ? 0 : dollarBalance.hashCode());
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
		if (accountAlias == null) {
			if (other.accountAlias != null)
				return false;
		} else if (!accountAlias.equals(other.accountAlias))
			return false;
		if (accountId != other.accountId)
			return false;
		if (btcBalance == null) {
			if (other.btcBalance != null)
				return false;
		} else if (!btcBalance.equals(other.btcBalance))
			return false;
		if (dollarBalance == null) {
			if (other.dollarBalance != null)
				return false;
		} else if (!dollarBalance.equals(other.dollarBalance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountAlias=" + accountAlias + ", dollarBalance=" + dollarBalance
				+ ", btcBalance=" + btcBalance + "]";
	}

}
