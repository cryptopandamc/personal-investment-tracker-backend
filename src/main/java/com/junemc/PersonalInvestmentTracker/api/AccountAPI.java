package com.junemc.PersonalInvestmentTracker.api;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junemc.PersonalInvestmentTracker.model.Account;
import com.junemc.PersonalInvestmentTracker.service.AccountService;

@RestController
@RequestMapping("/app/v1/account")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountAPI {

	private static final Logger log = LoggerFactory.getLogger(AccountAPI.class);

	@Autowired
	private AccountService accountService;

	@GetMapping("get/{accountId}")
	public ResponseEntity<Account> getAccount(@PathVariable("accountId") long accountId) {
		Optional<Account> account = accountService.retrieveAccountById(accountId);
		if (account.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(account.get());

	}

}
