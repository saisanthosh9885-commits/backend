package com.example.demo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ID")
	    private Long id;
	    @Column(name = "FirstName")
	    private String firstName;
	    @Column(name = "LastName")
	    private String lastName;
	    @Column(name = "Email")
	    private String email;
	    @Column(name = "Address")
	    private String address;
	    @Column(name = "Phone_No")
	    private Long phoneNumber;

}
