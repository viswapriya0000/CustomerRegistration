package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@SpringBootTest
public class CustomerServicesTest {
	
	@MockBean
	CustomerRepository cusRepo;
	
	@Autowired
	CustomerService cusService;
	
	@Autowired
	Customer customer;
	
	@Test
	public void customerMembership() {
		//Customer customer=new Customer();
		customer.setUserName("viswapriya@gmail.com");
		customer.setFullName("viswa priya");
		customer.setPassword("priya1234");
		customer.setGender("FEMALE");
		customer.setAilment("legpain");
		customer.setBloodGroup("O-");
		customer.setContactNo("1234567890");
		customer.setDateOfBirth("2000-08-11");
		String response="profile created successfully";
		when(cusRepo.save(customer)).thenReturn(customer);
		assertEquals(response,cusService.customerMembership(customer));
	}
	
	@Test
	public void customerView() {
		String userName="viswapriya@gmail.com";
		when(cusRepo.findByUserName(userName)).thenReturn(customer);
		assertEquals(Optional.of(customer),cusService.customerView(userName));
	}
	
	@Test
	public void getAllCustomers() {
		Customer cus1=new Customer();
		Customer cus2=new Customer();
		cus1.setUserName("viswapriya@gmail.com");
		cus1.setFullName("viswa priya");
		cus1.setPassword("priya1234");
		cus1.setGender("FEMALE");
		cus1.setAilment("legpain");
		cus1.setBloodGroup("O-");
		cus1.setContactNo("1234567890");
		cus1.setDateOfBirth("2000-08-11");
		
		cus2.setUserName("viswapriya12@gmail.com");
		cus2.setFullName("viswa priya");
		cus2.setPassword("priya1234");
		cus2.setGender("FEMALE");
		cus2.setAilment("legpain");
		cus2.setBloodGroup("O-");
		cus2.setContactNo("1234567890");
		cus2.setDateOfBirth("2000-08-11");
		
		when(cusRepo.findAll()).thenReturn(Stream.of(cus1,cus2).collect(Collectors.toList()));
		assertEquals(2,cusService.getAllCustomers().size());
	}
	
	
	

}
