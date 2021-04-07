package com.junemc.PersonalInvestmentTracker.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="BTC_DATA")
public class Btc {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "btc_gen")
	@SequenceGenerator(name = "btc_gen", sequenceName = "BTC_DATA_ID", allocationSize = 1)
	private long btcDataId;

	@Column
	private LocalDate tradeDate;

	@Column
	private long high;

	@Column
	private long low;

	public Btc(LocalDate tradeDate, long high, long low) {
		super();
		this.tradeDate = tradeDate;
		this.high = high;
		this.low = low;
	}

	public Btc() {
		super();
	}

	public long getBtcDataId() {
		return btcDataId;
	}

	public void setBtcDataId(long btcDataId) {
		this.btcDataId = btcDataId;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public long getHigh() {
		return high;
	}

	public void setHigh(long high) {
		this.high = high;
	}

	public long getLow() {
		return low;
	}

	public void setLow(long low) {
		this.low = low;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (btcDataId ^ (btcDataId >>> 32));
		result = prime * result + (int) (high ^ (high >>> 32));
		result = prime * result + (int) (low ^ (low >>> 32));
		result = prime * result + ((tradeDate == null) ? 0 : tradeDate.hashCode());
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
		Btc other = (Btc) obj;
		if (btcDataId != other.btcDataId)
			return false;
		if (high != other.high)
			return false;
		if (low != other.low)
			return false;
		if (tradeDate == null) {
			if (other.tradeDate != null)
				return false;
		} else if (!tradeDate.equals(other.tradeDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Btc [btcDataId=" + btcDataId + ", tradeDate=" + tradeDate + ", high=" + high + ", low=" + low + "]";
	}

}
