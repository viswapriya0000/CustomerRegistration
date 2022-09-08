package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exceptionhandler.PasswordInvalidException;
import com.example.demo.exceptionhandler.ProfileNotFoundException;
import com.example.demo.exceptionhandler.UsernameInvalidException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	CustomerRepository cusRepo;
	
	public String customerMembership(Customer customer) {
		 cusRepo.save(customer);
		 return ("profile created successfully");
		
	}
	
	public Optional<Customer> customerView(String userName) {
		try {
		     return Optional.of(cusRepo.findByUserName(userName));
		}
		catch(Exception e) {
		 			throw new ProfileNotFoundException("NO SUCH USERNAME");
 		}
		
	}
	
	public String loginPage(String userName,String password) {
		/*
		 * if(userName.equals(cusRepo.findByUserName(userName).getUserName())){
		 * if(password.equals(cusRepo.findByUserName(userName).getPassword())){ return
		 * "Welcome to HOME page"; } else { return "Wrong Password"; } } else { return
		 * "Please enter correct username"; }
		 */
		
		if(cusRepo.findByUserName(userName)==null) {
			return "Please enter correct username";
		}
		else {
			if(password.equals(cusRepo.findByUserName(userName).getPassword())){
				return "Welcome to HOME page";
			}
			else {
				return "Wrong Password";
			}
		}

	}
	
	public String forgotPassword(String userName, String password, String re_enter_password) {
		if (password.equals(re_enter_password)) {
			cusRepo.updatePassword(password, userName);
			return "Successfully changed the password";
		} else {
			return "re-enter-password doesnot matches with password";
		}
	}
	
	public List<Customer> getAllCustomers(){
		return cusRepo.findAll();
	}
	
	public String modify(Customer customer) {
		cusRepo.save(customer);
		return "updated successfully";
	}
	
	public String test(String userName,String password) {
	  if(cusRepo.findByUserName(userName)==null) {
		throw new UsernameInvalidException("please enter correct username");
		//return "Please enter correct username";
	  }
	  else {
		if(password.equals(cusRepo.findByUserName(userName).getPassword())){
			return "Welcome to HOME page";
		}
		else {
			throw new PasswordInvalidException("wrong password");
			//return "Wrong Password";
		}
	  }
	}
	
	
	public String customerDelete(String userName) {
		Customer customer = (cusRepo.findByUserName(userName));
		if (customer!=null) {
			cusRepo.deleteByUserName(userName);
			return "Customer is deleted with username " + userName;
		} 
		else {
			throw new UsernameInvalidException("Customer is not found for this username " + userName);
		}

	}
	 

}
