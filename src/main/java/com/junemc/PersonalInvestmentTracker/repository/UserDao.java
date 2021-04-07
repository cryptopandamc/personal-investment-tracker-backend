package com.junemc.PersonalInvestmentTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junemc.PersonalInvestmentTracker.model.User;

public interface UserDao extends JpaRepository<User, Long> {

	
}
