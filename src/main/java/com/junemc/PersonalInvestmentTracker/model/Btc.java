package com.junemc.PersonalInvestmentTracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "BTC_DATA")
public class Btc {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "btc_gen")
	@SequenceGenerator(name = "btc_gen", sequenceName = "BTC_DATA_ID", allocationSize = 1)
	private long btcDataId;

	@Column
	private LocalDate tradeDate;

	@Column
	private BigDecimal high;

	@Column
	private BigDecimal low;

	public Btc(LocalDate tradeDate, BigDecimal high, BigDecimal low) {
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

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getLow() {
		return low;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (btcDataId ^ (btcDataId >>> 32));
		result = prime * result + ((high == null) ? 0 : high.hashCode());
		result = prime * result + ((low == null) ? 0 : low.hashCode());
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
		if (high == null) {
			if (other.high != null)
				return false;
		} else if (!high.equals(other.high))
			return false;
		if (low == null) {
			if (other.low != null)
				return false;
		} else if (!low.equals(other.low))
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
