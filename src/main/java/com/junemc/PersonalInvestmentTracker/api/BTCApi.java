package com.junemc.PersonalInvestmentTracker.api;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.junemc.PersonalInvestmentTracker.model.Btc;
import com.junemc.PersonalInvestmentTracker.service.BtcService;

@RestController
@RequestMapping("/api/v1/btc")
@CrossOrigin(origins = "http://localhost:3000")
public class BTCApi {

	@Autowired
	private BtcService btcService;

	@GetMapping("/GetBtc")
	Page<Btc> getBtc(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy) {
		return btcService.findAll(PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy.orElse("btcDataId")));
	}
	
	// example of a test url that works with date passed in http://localhost:8088//api/v1/btc/GetDayData?tradeDate=2013-10-01
	@GetMapping("GetDayData")
	Btc getBtc( @RequestParam(value = "tradeDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
		return btcService.getDayData(tradeDate);
	}

}
