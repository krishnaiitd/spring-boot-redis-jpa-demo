package com.redis.example.redisjpademo.controllers;

import com.redis.example.redisjpademo.inmemoryModels.Customer;
import com.redis.example.redisjpademo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity getCustomers() {

        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
