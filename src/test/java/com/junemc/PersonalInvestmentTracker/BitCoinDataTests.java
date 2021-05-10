package com.junemc.PersonalInvestmentTracker;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	private Account account;

	private User user;

	@BeforeEach
	void setUp() {
		BigDecimal dollarBalance = new BigDecimal(0);
		BigDecimal btcBalance = new BigDecimal(0);
		account = new Account("Test account", dollarBalance, btcBalance);
		accounts.add(account);
		user = new User("Fred", "Flintstone", account);
		userService.save(user);
	}

	@Test
	void test_ThatASingleBitCoinDataPoint_CanReadFromImportedData() {
		Btc btcDataPoint = btcService.retrieveOne(1).get();
		assertTrue(btcDataPoint.getBtcDataId() > 0);
	}

	// this test requires a user
	// prob be two step in the app, show price, show num sats can be bought, confirm
	@Test
	void test_ThatADailyHighProce_CanBereturned() {
		Btc btc = btcService.getPrice(LocalDate.of(2013, 10, 01));
		BigDecimal expected = new BigDecimal("124.75");
		BigDecimal btcPriceHigh = btc.getHigh();
		assertEquals(expected, btcPriceHigh);
	}

	@Test
	void test_ThatADailyLowPrice_CanBeReturned_ForAGivenDate() {
		Btc btc = btcService.getPrice(LocalDate.of(2013, 10, 01));
		BigDecimal btcPriceLow = btc.getLow().setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal expected = new BigDecimal(122.56).setScale(2, RoundingMode.HALF_EVEN);
		assertEquals(expected, btcPriceLow);
	}

	@Test
	void test_ThatABTCAmount_forADollarAmount_CanBeObtainedUsingFullDollarBalance() {
		Btc btc = btcService.getPrice(LocalDate.of(2013, 10, 01));
		BigDecimal dollarBalanceToAdd = new BigDecimal(1000.00).setScale(2, RoundingMode.HALF_DOWN);
		account.setDollarBalance(dollarBalanceToAdd);
		BigDecimal dollarTradeAmount = account.getDollarBalance().setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal btcPriceHigh = btc.getHigh().setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal btcToBuyAthighPrice = dollarTradeAmount.divide(btcPriceHigh, 2, RoundingMode.HALF_DOWN);
		BigDecimal amountOfBtcToBuy = new BigDecimal((8.02)).setScale(2, RoundingMode.HALF_DOWN);
		assertEquals(amountOfBtcToBuy, btcToBuyAthighPrice);
	}
	
	@Test
	void test_ThatBTC_CanBeBoughtByReducingDollarBalanceAndIncreasingBTCBalance() {
		Btc btc = btcService.getPrice(LocalDate.of(2013, 10, 01));
		BigDecimal dollarBalanceToAdd = new BigDecimal(1000.00).setScale(2, RoundingMode.HALF_DOWN);
		account.setDollarBalance(dollarBalanceToAdd);
		BigDecimal dollarTradeAmount = account.getDollarBalance().setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal btcPriceHigh = btc.getHigh().setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal btcToBuyAthighPrice = dollarTradeAmount.divide(btcPriceHigh, 2, RoundingMode.HALF_DOWN);
		BigDecimal expectedBtcBalance = new BigDecimal((8.02)).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal expectedDollarBalance = new BigDecimal((0.00)).setScale(2, RoundingMode.HALF_DOWN);

		BigDecimal initialBTCBalance = account.getBtcBalance();
		account.setBtcBalance(initialBTCBalance.add(btcToBuyAthighPrice) );
		account.setDollarBalance(account.getDollarBalance().subtract(dollarTradeAmount)); 
		accountService.save(account);
		assertAll( "account",
			    () -> assertEquals(expectedBtcBalance, account.getBtcBalance()),
			    () -> assertEquals(expectedDollarBalance, account.getDollarBalance())
		);
	}

}
