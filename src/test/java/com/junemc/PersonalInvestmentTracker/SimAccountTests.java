package com.junemc.PersonalInvestmentTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.junemc.PersonalInvestmentTracker.model.Account;
import com.junemc.PersonalInvestmentTracker.service.AccountService;


@SpringBootTest
class SimAccountTests {
	
	@Autowired 
	private AccountService accountService;

	@Test
	void contextLoads() {
	}

	
	@Test
	void test_ThatAUserCanAddFiatFunds() {
		Account testAccount = new Account("sim account", 0.00);
		testAccount.setBalance(1000.00);
		accountService.save(testAccount);
		Account accountFromDb = accountService.retrieveAccountById(2l).get();
		assertEquals(1000.00, accountFromDb.getBalance());
	}
	
	@Test
	void test_ThatADepositAmountCanBeIncreased() {
		Account accountToUpdate = accountService.retrieveAccountById(1l).get();
		double currentBalance = accountToUpdate.getBalance();
		currentBalance = currentBalance + 1000;
		accountService.update(accountToUpdate);
		Account updatedAccount = accountService.retrieveAccountById(1l).get();
		double updatedBalance = updatedAccount.getBalance();
		assertNotEquals(currentBalance, updatedBalance);
	}
	
	@Test
	void test_ThatADepositCanBeDecreased() {
		Account accountToUpdate = accountService.retrieveAccountById(1l).get();
		double currentBalance = accountToUpdate.getBalance();
		currentBalance = currentBalance - 1500;
		accountService.update(accountToUpdate);
		Account updatedAccount = accountService.retrieveAccountById(1l).get();
		double updatedBalance = updatedAccount.getBalance();
		assertNotEquals(currentBalance, updatedBalance);
	}
	
	
	
 }
