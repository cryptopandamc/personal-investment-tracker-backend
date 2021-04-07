package com.junemc.PersonalInvestmentTracker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.junemc.PersonalInvestmentTracker.model.Btc;
import com.junemc.PersonalInvestmentTracker.service.BtcService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BitCoinDataTests {

	
	@Autowired
	private BtcService btcService;
	
	@Test
	void test_ThatASingleBitCoinDataPoint_CanReadFromImportedData() {
		Btc btcDataPoint = btcService.retrieveOne(1).get();
		assertTrue(btcDataPoint.getBtcDataId() >0);
	}
	
}
