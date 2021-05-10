package com.junemc.PersonalInvestmentTracker.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.junemc.PersonalInvestmentTracker.model.Btc;

public interface BtcDao extends JpaRepository<Btc, Long> {

	Btc findByTradeDate(LocalDate dateOfPurchase);
	
	Page<Btc> findAll(Pageable pageable);

}
