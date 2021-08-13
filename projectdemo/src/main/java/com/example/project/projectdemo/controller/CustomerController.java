package com.example.project.projectdemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.project.projectdemo.model.Customer;
import com.example.project.projectdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;


	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
		Optional<Customer> customerDetails = customerRepository.findById(id);

		if (customerDetails.isPresent()) {
			return new ResponseEntity<>(customerDetails.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		try {
			Customer createCustomer = customerRepository
					.save(new Customer(customer.getFirstName(), customer.getLastName(), customer.getAddress()));
			return new ResponseEntity<>(createCustomer, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateTutorial(@PathVariable("id") long id, @RequestBody Customer customer) {
		Optional<Customer> customerDetails = customerRepository.findById(id);

		if (customerDetails.isPresent()) {
			Customer customerUpdate = customerDetails.get();
			customerUpdate.setFirstName(customer.getFirstName());
			customerUpdate.setLastName(customer.getLastName());
			customerUpdate.setAddress(customer.getAddress());
			return new ResponseEntity<>(customerRepository.save(customerUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			customerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
