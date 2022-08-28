package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.CustomerDTO;
import com.masai.service.UserLogInImpl;

@RestController
public class LoginController {
	
	@Autowired
	private UserLogInImpl userLogIn;

	// for user Login
	@PostMapping(value = "/login")
	public String logInCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		return userLogIn.logIntoAccount(customerDTO);
	}
	
	// for user Logout
	@PatchMapping(value = "/logout")
	public String logOutCustomer(@RequestParam(required = false) String key) {
		return userLogIn.logOutFromAccount(key);
	}

}