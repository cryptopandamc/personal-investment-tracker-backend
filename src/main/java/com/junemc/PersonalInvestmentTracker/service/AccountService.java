package com.junemc.PersonalInvestmentTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junemc.PersonalInvestmentTracker.model.Account;
import com.junemc.PersonalInvestmentTracker.repository.AccountDao;

@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;

	public AccountService(AccountDao accountDao) {
		super();
		this.accountDao = accountDao;
	}

	public void save(Account account) {

		accountDao.save(account);

	}

	public Optional<Account> retrieveAccountById(long accountId) {
		return accountDao.findById(accountId);
	}

	public Account update(Account account) {
		return accountDao.save(account);
	}

}
