package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Component


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="CUSTOMER")
public @Data class Customer {
    
	
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long userId;
	 */
	@Id
	@NotBlank(message="userName should not be blank")
	//@Column(unique=true)
	@Column(name="USERNAME")
	private String userName;//username is generally equivalent to mail id
	
    @Column(name="FULLNAME")
	@NotBlank(message="fullName should not be blank")
	private String fullName;
	
    @Column(name="PASSWORD")
	@NotBlank(message="password should not be blank")
	@Size(min=8,message="password must contains 8 characters")
	private String password;
	
    @Column(name="GENDER")
	@NotBlank(message="gender should not be blank")
	@Pattern(regexp="^(MALE|FEMALE)$",message="Please enter MALE or FEMALE")
	private String gender;
	
    @Column(name="CONTACTNO")
	@NotBlank(message="contactNo should not be blank")
	@Pattern(regexp="(^$|[0-9]{10})", message="contactNo must have 10 digits as well as only digits")
	private String contactNo;
	
    @Column(name="BLOODGROUP")
	@NotBlank(message="bloodgroup should not be blank")
	@Pattern(regexp="^(A+|A-|B+|B-|AB+|AB-|O+|O-)$", message="Enter valid blood group")
	private String bloodGroup;
	
    @Column(name="AILEMENT")
	@NotBlank(message="ailment should not be blank")
	private String ailment;
	
    @Column(name="DATEOFBIRTH")
	@NotBlank(message="ailment should not be blank")
	@Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$",message="Enter date of birth in this format yyyy-mm-dd")
	private String dateOfBirth;


}
