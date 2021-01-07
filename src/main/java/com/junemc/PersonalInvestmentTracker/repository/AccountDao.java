package com.junemc.PersonalInvestmentTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junemc.PersonalInvestmentTracker.model.Account;

public interface AccountDao  extends JpaRepository<Account, Long>{

	

}
