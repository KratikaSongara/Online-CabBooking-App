package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.masai.service.CustomerService;
import com.masai.entity.Customer;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/customer/{Id}")
	public Customer getCustomer(@PathVariable("Id") Integer id)
	{    
		return service.findCustomer(id);
	}
	@GetMapping("/customers")
	public List<Customer> getAllCustomer()
	{    
		return service.allCustomer();
	}
	@GetMapping("/customer/{Email}/{Password}")
	public Customer getAllCustomer(@PathVariable("Email") String Email,@PathVariable("Password")String Password)
	{    
		return service.vaildCustomer(Email, Password);
	}
	
	@PostMapping(value = "/save",consumes = "application/json")
	public Customer SaveStudent(@Valid @RequestBody Customer customer)
	{	System.out.println(customer);
		return service.saveCustomer(customer);
	}
	@PutMapping("/update/{id}")
	public Customer updateStudent(@Valid @RequestBody Customer customer,@PathVariable("id")Integer id )
	{
		
		return service.updateCustomer(customer,id);
	}
    @DeleteMapping("/customer/delete/{id}")
	public String delete(@PathVariable("id")Integer id) {
		
    	return service.deleteCustomer(id);
	}
}
