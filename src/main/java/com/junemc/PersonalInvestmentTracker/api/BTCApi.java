package com.junemc.PersonalInvestmentTracker.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.junemc.PersonalInvestmentTracker.model.Btc;
import com.junemc.PersonalInvestmentTracker.service.BtcService;

@RestController
@RequestMapping("/api/v1/btc")
@CrossOrigin(origins = "http://localhost:3000")
public class BTCApi {

	@Autowired
	private BtcService btcService;

	Pageable firstPageWithTenElements = PageRequest.of(0, 10);

	@GetMapping(value="AllBtcData", params = { "page", "size" })
	public List<Btc> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size,
			UriComponentsBuilder uriBuilder, HttpServletResponse response) {
		Page<Btc> resultPage = btcService.findAll(firstPageWithTenElements);
		if (page > resultPage.getTotalPages()) {
			System.err.println("something went wrong");
		}
		return resultPage.getContent();
	}
}
