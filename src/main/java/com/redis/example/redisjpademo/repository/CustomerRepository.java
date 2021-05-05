package com.redis.example.redisjpademo.repository;

import com.redis.example.redisjpademo.inmemoryModels.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByCustomerId(Integer customerId);
    List<Customer> findByFirstname(String firstname);
    List<Customer> findByFirstnameAndLastname(String firstname,
                                              String lastname);

    List<Customer> findAll();


}
