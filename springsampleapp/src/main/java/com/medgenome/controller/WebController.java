package com.medgenome.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medgenome.model.Customer;
import com.medgenome.repo.CustomerRepository;


@RestController
public class WebController {

	@Autowired
	CustomerRepository repository;

	@RequestMapping("/save")
	public String process() {
		repository.save(new Customer("Jack", "Smith"));
		repository.save(new Customer("Adam", "Johnson"));
		repository.save(new Customer("Kim", "Smith"));
		repository.save(new Customer("David", "Williams"));
		repository.save(new Customer("Peter", "Davis"));
		return "Done";
	}

	@Transactional(readOnly = true)
	@RequestMapping("/findall")
	public String findAllByStream() {
		List<String> mapstream = Collections.emptyList();

		try (Stream<Customer> stream = repository.findAllCustomers()) {
			mapstream = stream.map(customer -> customer.toString()).collect(Collectors.toList());
		}

		return mapstream.toString();
	}
	
	@Transactional(readOnly = true)
	@RequestMapping("/findalllastname")
	public String findAllLastNameByStream() {
		List<String> mapstream = Collections.emptyList();;

		try (Stream<String> stream = repository.findAllandShowLastName()) {
			mapstream = stream.collect(Collectors.toList());
		}

		return mapstream.toString();
	}

	@Transactional(readOnly = true)
	@RequestMapping("/findbylastname")
	public String fetchDataByLastNameWithStream(@RequestParam("lastname") String lastName) {
		List<String> mapstream = Collections.emptyList();

		try (Stream<Customer> stream = repository.findByLastName(lastName)) {
			mapstream = stream.map(customer -> customer.toString()).collect(Collectors.toList());
		}

		return mapstream.toString();
	}

}
