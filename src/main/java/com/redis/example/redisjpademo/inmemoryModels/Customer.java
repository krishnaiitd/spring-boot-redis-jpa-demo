package com.redis.example.redisjpademo.inmemoryModels;

import com.redis.example.redisjpademo.config.RedisSequence;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Example of Redis Cache with each object can have different TTL
 */
@RedisHash(value = "customer")
@Data
public class Customer {

    @Id
    private Integer id =
            1000000 + Math.toIntExact(RedisSequence.getSequence().longValue());

    @Indexed
    private Integer customerId;
    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    private Integer age;

    private Date createDatetime;

    private Date updatedDatetime;

    // This will be set the expiry of object to 1 days by default and can be
    // changed while creating obejct.
    @TimeToLive(unit = TimeUnit.DAYS)
    private Integer expiration = 1;

}
