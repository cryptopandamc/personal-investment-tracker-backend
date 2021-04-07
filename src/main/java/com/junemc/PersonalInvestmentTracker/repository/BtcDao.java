package com.junemc.PersonalInvestmentTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junemc.PersonalInvestmentTracker.model.Btc;

public interface  BtcDao extends JpaRepository<Btc, Long> {

}
