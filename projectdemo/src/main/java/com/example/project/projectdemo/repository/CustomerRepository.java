package com.example.project.projectdemo.repository;

import java.util.List;

import com.example.project.projectdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
