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

	BigDecimal btcPriceHigh;// = new BigDecimal(0.00);

	BigDecimal btcPriceLow;// = new BigDecimal(0.00);
	
	BigDecimal dollarTradeAmount ;

	BigDecimal zeroDollarBalance;
	
	Btc btc;

	@BeforeEach
	void setUp() {
		zeroDollarBalance = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_DOWN);;
		BigDecimal btcBalance = new BigDecimal(0);
		account = new Account("Test account", zeroDollarBalance, btcBalance);
		accounts.add(account);
		user = new User("Fred", "Flintstone", account);
		userService.save(user);
		btc = btcService.getPrice(LocalDate.of(2013, 10, 01));
		btcPriceHigh = btc.getHigh().setScale(2, RoundingMode.HALF_DOWN);
		btcPriceLow = btc.getLow().setScale(2, RoundingMode.HALF_DOWN);
		dollarTradeAmount = new BigDecimal(1000.00).setScale(2, RoundingMode.HALF_DOWN);
	}

	@Test
	void test_ThatASingleBitCoinDataPoint_CanReadFromImportedData() {
		Btc btcDataPoint = btcService.retrieveOne(1).get();
		assertTrue(btcDataPoint.getBtcDataId() > 0);
	}

	@Test
	void test_ThatADailyHighProce_CanBereturned() {
		BigDecimal expected = new BigDecimal("124.75");
		assertEquals(expected, btcPriceHigh);
	}

	@Test
	void test_ThatADailyLowPrice_CanBeReturned_ForAGivenDate() {
		BigDecimal btcPriceLow = btc.getLow().setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal expected = new BigDecimal(122.56).setScale(2, RoundingMode.HALF_DOWN);
		assertEquals(expected, btcPriceLow);
	}

	@Test
	void test_ThatABTCAmount_forADollarAmount_CanBeObtainedUsingFullDollarBalance() {
		account.setDollarBalance(dollarTradeAmount);
		BigDecimal dollarTradeAmount = account.getDollarBalance().setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal btcToBuyAthighPrice = dollarTradeAmount.divide(btcPriceHigh, 2, RoundingMode.HALF_DOWN);
		BigDecimal amountOfBtcToBuy = new BigDecimal((8.02)).setScale(2, RoundingMode.HALF_DOWN);
		assertEquals(amountOfBtcToBuy, btcToBuyAthighPrice);
	}

	@Test
	void test_ThatBTC_CanBeBoughtByReducingDollarBalanceAndIncreasingBTCBalance() {
		account.setDollarBalance(dollarTradeAmount);
		BigDecimal dollarTradeAmount = account.getDollarBalance().setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal btcToBuyAthighPrice = dollarTradeAmount.divide(btcPriceHigh, 2, RoundingMode.HALF_DOWN);
		BigDecimal expectedBtcBalance = new BigDecimal((8.02)).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal expectedDollarBalance = new BigDecimal((0.00)).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal initialBTCBalance = account.getBtcBalance();
		account.setBtcBalance(initialBTCBalance.add(btcToBuyAthighPrice));
		account.setDollarBalance(account.getDollarBalance().subtract(dollarTradeAmount));
		accountService.save(account);
		assertAll("account", () -> assertEquals(expectedBtcBalance, account.getBtcBalance()),
				() -> assertEquals(expectedDollarBalance, account.getDollarBalance()));
	}

	@Test
	void test_ThatADifferentBTCAMountIsBoughtUsingTheDailyLow() {
		account.setDollarBalance(dollarTradeAmount);
		BigDecimal dollarTradeAmount = account.getDollarBalance().setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal btcToBuyAtLowPrice = dollarTradeAmount.divide(btcPriceLow, 2, RoundingMode.HALF_DOWN);
		BigDecimal expectedBtcBalance = new BigDecimal((8.16)).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal expectedDollarBalance = new BigDecimal((0.00)).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal initialBTCBalance = account.getBtcBalance();
		account.setBtcBalance(initialBTCBalance.add(btcToBuyAtLowPrice));
		account.setDollarBalance(account.getDollarBalance().subtract(dollarTradeAmount));
		accountService.save(account);
		assertAll("account", () -> assertEquals(expectedBtcBalance, account.getBtcBalance()),
				() -> assertEquals(expectedDollarBalance, account.getDollarBalance()));

	}

	@Test
	void test_ThatBTCCanBeExchangedForDollarsUsingTheDailyHigh() {
		BigDecimal btcBalanceToStartTrade = dollarTradeAmount.divide(btcPriceHigh, 2, RoundingMode.HALF_DOWN);
		account.setBtcBalance(btcBalanceToStartTrade);
		accountService.save(account);
		BigDecimal btcToTrade = account.getBtcBalance();
		BigDecimal dollarBalanceAfterTrade = btcPriceHigh.multiply(btcToTrade).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal expectedDollarValueAfterTrade =dollarBalanceAfterTrade;
		account.setDollarBalance(dollarBalanceAfterTrade);
		account.setBtcBalance(btcBalanceToStartTrade.subtract(btcBalanceToStartTrade));
		accountService.save(account);
		assertAll("account", () -> assertEquals(expectedDollarValueAfterTrade, dollarBalanceAfterTrade),
				() -> assertEquals(zeroDollarBalance, account.getBtcBalance()));
	}

}
