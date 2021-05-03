package com.redis.example.redisjpademo.repository;

import com.redis.example.redisjpademo.inmemory.TestingRedisObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRedisObjectRepository extends CrudRepository<TestingRedisObject, Integer> {

    Optional<TestingRedisObject> findByIdSiswa(long idSiswa);
}
