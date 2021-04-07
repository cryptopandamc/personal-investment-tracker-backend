package com.junemc.PersonalInvestmentTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junemc.PersonalInvestmentTracker.model.User;
import com.junemc.PersonalInvestmentTracker.repository.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void save(User user) {
		userDao.save(user);
	}
}
