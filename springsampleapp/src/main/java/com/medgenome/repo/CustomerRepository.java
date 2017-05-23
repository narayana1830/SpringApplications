package com.medgenome.repo;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.medgenome.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Query("select c from Customer c")
	Stream<Customer> findAllCustomers();
	
	@Query("select c.lastName from Customer c")
	Stream<String> findAllandShowLastName();

	@Query("select c from Customer c where c.lastName = ?1")
	Stream<Customer> findByLastName(String lastName);
}
