package com.redis.example.redisjpademo.services;

import com.redis.example.redisjpademo.inmemoryModels.TestingRedisObject;
import com.redis.example.redisjpademo.repository.TestRedisObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestRedisObjectService {


    @Autowired
    private TestRedisObjectRepository testRedisObjectRepository;

    public void save() {
        TestingRedisObject testingRedisObject = new TestingRedisObject();
        testingRedisObject.setIdJadwalProfil(123);
        testingRedisObject.setIdProfil(1111);
        testingRedisObject.setIdSiswa(12345);

        testRedisObjectRepository.save(testingRedisObject);
    }

    public TestingRedisObject getByIdSiswa() {
        return testRedisObjectRepository.findByIdSiswa(12345).get();
    }

    public void deleteAll() {
        testRedisObjectRepository.deleteAll();
    }

}
