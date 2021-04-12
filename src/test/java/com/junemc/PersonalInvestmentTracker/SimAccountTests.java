package com.junemc.PersonalInvestmentTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

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
		BigDecimal dollarBalance = new BigDecimal(0);
		BigDecimal btcBalance = new BigDecimal(0);
		BigDecimal balanceToAdd = new BigDecimal(1000);
		Account testAccount = new Account("sim account", dollarBalance, btcBalance);
		testAccount.setDollarBalance(dollarBalance.add(balanceToAdd));
		accountService.save(testAccount);
		Account accountFromDb = accountService.retrieveAccountById(2l).get();
		assertEquals(1000.00, accountFromDb.getDollarBalance());
	}
	
	@Test
	void test_ThatADepositAmountCanBeIncreased() {
		Account accountToUpdate = accountService.retrieveAccountById(1l).get();
		BigDecimal currentBalance = accountToUpdate.getDollarBalance();
		BigDecimal balanceToAdd = new BigDecimal(1000);
		currentBalance = currentBalance.add(balanceToAdd);
		accountService.update(accountToUpdate);
		Account updatedAccount = accountService.retrieveAccountById(1l).get();
		BigDecimal updatedBalance = updatedAccount.getDollarBalance();
		assertNotEquals(currentBalance, updatedBalance);
	}
	
	@Test
	void test_ThatADepositCanBeDecreased() {
		Account accountToUpdate = accountService.retrieveAccountById(1l).get();
		BigDecimal currentBalance = accountToUpdate.getDollarBalance();
		BigDecimal balanceToSubtract = new BigDecimal(500);
		currentBalance = currentBalance.subtract(balanceToSubtract);
		accountService.update(accountToUpdate);
		Account updatedAccount = accountService.retrieveAccountById(1l).get();
		BigDecimal updatedBalance = updatedAccount.getDollarBalance();
		assertNotEquals(currentBalance, updatedBalance);
	}
	
	
	
	
	
 }
