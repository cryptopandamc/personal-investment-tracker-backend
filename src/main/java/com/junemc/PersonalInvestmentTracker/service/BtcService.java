package com.junemc.PersonalInvestmentTracker.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.junemc.PersonalInvestmentTracker.model.Btc;
import com.junemc.PersonalInvestmentTracker.repository.BtcDao;

@Service
public class BtcService {
	
	@Autowired 
	private BtcDao btcDao;

	public Optional<Btc> retrieveOne(long btcDataId) {
		return btcDao.findById(btcDataId);
	}

	public Btc getDayData(LocalDate dateOfPurchase) {
		return btcDao.findByTradeDate(dateOfPurchase);
	}
	
	public Page<Btc> findAll( Pageable pageable) {
		return btcDao.findAll(pageable);
	}
}
