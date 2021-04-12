package com.junemc.PersonalInvestmentTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.junemc.PersonalInvestmentTracker.model.Account;
import com.junemc.PersonalInvestmentTracker.model.Btc;
import com.junemc.PersonalInvestmentTracker.model.User;
import com.junemc.PersonalInvestmentTracker.service.AccountService;
import com.junemc.PersonalInvestmentTracker.service.BtcService;
import com.junemc.PersonalInvestmentTracker.service.UserService;


@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BitCoinDataTests {

	
	@Autowired
	private BtcService btcService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	List<Account> accounts = new ArrayList<>();
	
	private User user;
	
	@BeforeEach
	void setUp() {
		BigDecimal dollarBalance = new BigDecimal(0);
		BigDecimal btcBalance = new BigDecimal(0);
		Account account = new Account("Test account", dollarBalance, btcBalance);
		accounts.add(account);
		user = new User("Fred", "Flintstone", account);
		userService.save(user);
	}
	
	@Test
	void test_ThatASingleBitCoinDataPoint_CanReadFromImportedData() {
		Btc btcDataPoint = btcService.retrieveOne(1).get();
		assertTrue(btcDataPoint.getBtcDataId() >0);
	}
	
	//this test requires a user
	// prob be two step in the app, show price, show num sats can be bought, confirm
	@Test
	void test_ThatADailyHighProce_CanBereturned() {
		Btc btc = btcService.getPrice(LocalDate.of(2013, 10, 01));
		long btcPriceHigh = btc.getHigh();
		assertEquals(125, btcPriceHigh);
	}
	
	@Test
	void test_ThatADailyLowPrice_CanBeReturned_ForAGivenDate() {
		Btc btc = btcService.getPrice(LocalDate.of(2013, 10, 01));
		long btcPriceHigh = btc.getLow();
		assertEquals(123, btcPriceHigh);
	}
}
