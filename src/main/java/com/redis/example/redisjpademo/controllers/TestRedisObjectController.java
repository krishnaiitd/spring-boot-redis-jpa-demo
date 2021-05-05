package com.redis.example.redisjpademo.controllers;

import com.redis.example.redisjpademo.inmemoryModels.TestingRedisObject;
import com.redis.example.redisjpademo.services.TestRedisObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedisObjectController {

    @Autowired
    TestRedisObjectService testRedisObjectService;

    @RequestMapping(value = "test/redis/object", method = RequestMethod.POST)
    public ResponseEntity createDummyObject() {

        testRedisObjectService.save();

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/test/redis/object/{idSiswa}", method = RequestMethod.GET)
    public ResponseEntity<TestingRedisObject> getObjectBySiswaId(@PathVariable long idSiswa) {
        TestingRedisObject testingRedisObject =
                testRedisObjectService.getByIdSiswa();

        return ResponseEntity.ok().body(testingRedisObject);
    }
}
