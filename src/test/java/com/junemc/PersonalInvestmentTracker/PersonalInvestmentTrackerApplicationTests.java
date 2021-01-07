package com.junemc.PersonalInvestmentTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.junemc.PersonalInvestmentTracker.model.Account;
import com.junemc.PersonalInvestmentTracker.service.AccountService;


@SpringBootTest
class PersonalInvestmentTrackerApplicationTests {
	
	@Autowired 
	private AccountService accountService;

	@Test
	void contextLoads() {
	}

	
	@Test
	void test_ThatAUserCanAddFiatFunds() {
		Account testAccount = new Account(0.00);
		testAccount.setBalance(1000.00);
		accountService.save(testAccount);
		Account accountFromDb = accountService.retrieveAccountById(1l).get();
		assertEquals(1000.00, accountFromDb.getBalance());
	}
 }
