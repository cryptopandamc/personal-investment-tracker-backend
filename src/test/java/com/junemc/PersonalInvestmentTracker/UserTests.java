package com.junemc.PersonalInvestmentTracker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.junemc.PersonalInvestmentTracker.model.Account;
import com.junemc.PersonalInvestmentTracker.model.User;
import com.junemc.PersonalInvestmentTracker.service.AccountService;
import com.junemc.PersonalInvestmentTracker.service.UserService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserTests {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	@Test
	void test_ThatAUserCanBeCreated() {
		BigDecimal dollarBalance = new BigDecimal(1000);
		BigDecimal btcBalance = new BigDecimal(0);
		Account account = new Account("June's account", dollarBalance,  btcBalance);
		accountService.save(account);
		User user = new User("june", "mc", account);
		userService.save(user);
		assertTrue(user.getUserId() > 0);
	}
	
	
}
