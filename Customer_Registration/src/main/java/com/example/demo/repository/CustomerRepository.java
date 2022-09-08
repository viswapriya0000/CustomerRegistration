package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

	public Customer findByUserName(String userName); 
	public Customer findByPassword(String password); 
	
	@Modifying
	@Query("DELETE Customer c WHERE c.userName=?1")
	public void deleteByUserName(String userName);
	
	
	@Modifying

	@Query("update Customer c set c.password=?1 where c.userName=?2")
	public void updatePassword(String password, String userName);
 
}
