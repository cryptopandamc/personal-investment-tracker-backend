package com.junemc.PersonalInvestmentTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junemc.PersonalInvestmentTracker.model.Account;

public interface AccountDao extends JpaRepository<Account, Long> {
	
	/*
	public default  Optional<Account> update(Account account){
		if (this.findById(account.getAccountId()).isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(account);
	}
	*/

}
