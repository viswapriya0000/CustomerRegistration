package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/Customer")
//@Validated
public class CustomerController {
	
	@Autowired
	CustomerService cusService;
	
	@PostMapping("/Sign up")
	public ResponseEntity<String> addCustomerDetails(@Validated @RequestBody Customer customer){
	    String response=cusService.customerMembership(customer);
		return new ResponseEntity<String>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/View Profile")
	public ResponseEntity<Customer> fetchDetails(@RequestParam("username")String userName){
		Optional<Customer> customer=cusService.customerView(userName);
		return new ResponseEntity<Customer>(customer.get(),HttpStatus.OK);
	}
	
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("username")String userName,@RequestParam("password")String password){
		String response=cusService.loginPage(userName, password);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@GetMapping("/forgot password")
	public ResponseEntity<String> passwordReset(@RequestParam("username") String userName,
			@RequestParam("password") String password, @RequestParam("Re_enter_password") String re_enter_password) {
		String response = cusService.forgotPassword(userName, password, re_enter_password);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/patients list")
	public ResponseEntity<List<Customer>> patientsList(){
		List<Customer> customerObj= cusService.getAllCustomers();
		  return new ResponseEntity<List<Customer>>(customerObj,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Customer customer){
		String response=cusService.modify(customer);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> test1(@RequestParam("username")String userName,@RequestParam("password")String password){
		String response=cusService.loginPage(userName, password);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/Delete Profile")
	public ResponseEntity<String> deleteProfile(@RequestParam("username") String userName) {
		String response = cusService.customerDelete(userName);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
