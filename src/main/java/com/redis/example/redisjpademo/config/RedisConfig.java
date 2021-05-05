package com.redis.example.redisjpademo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
@ComponentScan("entity.inmemory")
@EnableRedisRepositories(basePackages = "repository")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String REDIS_HOST;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;

    @Value("${spring.redis.pool.max-active}")
    private int REDIS_POOL_MAX_ACTIVE;

    @Value("${spring.redis.pool.max-idle}")
    private int REDIS_POOL_MAX_IDLE;

    @Value("${spring.redis.pool.min-idle}")
    private int REDIS_POOL_MIN_IDLE;

    @Value("${spring.redis.pool.max-wait}")
    private long REDIS_POOL_TIMEOUT;

    @Value("${spring.redis.timeout}")
    private int REDIS_TIMEOUT;

    Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //Maximum number of active connections that can be allocated from this pool at the same time
        poolConfig.setMaxTotal(REDIS_POOL_MAX_ACTIVE);
        //Number of connections to Redis that just sit there and do nothing
        poolConfig.setMaxIdle(REDIS_POOL_MAX_IDLE);
        //Minimum number of idle connections to Redis - these can be seen as always open and ready to serve
        poolConfig.setMinIdle(REDIS_POOL_MIN_IDLE);
        //The maximum number of milliseconds that the pool will wait (when there are no available connections) for a connection to be returned before throwing an exception
        poolConfig.setMaxWaitMillis(REDIS_POOL_TIMEOUT);
        //The minimum amount of time an object may sit idle in the pool before it is eligible for eviction by the idle object evictor
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        //The minimum amount of time a connection may sit idle in the pool before it is eligible for eviction by the idle connection evictor
        poolConfig.setSoftMinEvictableIdleTimeMillis(Duration.ofSeconds(10).toMillis());
        //Idle connection checking period
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(5).toMillis());
        //Maximum number of connections to test in each idle check
        poolConfig.setNumTestsPerEvictionRun(3);
        //Tests whether connection is dead when connection retrieval method is called
        poolConfig.setTestOnBorrow(true);
        //Tests whether connection is dead when returning a connection to the pool
        poolConfig.setTestOnReturn(true);
        //Tests whether connections are dead during idle periods
        poolConfig.setTestWhileIdle(true);
        poolConfig.setBlockWhenExhausted(true);

        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory(poolConfig);
        jedisConFactory.setHostName(REDIS_HOST);
        jedisConFactory.setPort(REDIS_PORT);
//        jedisConFactory.
        return jedisConFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

//        template.expire("2592000000", Duration.ofDays(30));
        return template;
    }

//    @Bean
//    public RedisCacheConfiguration cacheConfiguration() {
//
//        log.info("Info -> Redis Cache Configuration");
//
//        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofDays(30));
//
//        log.info("Duration -> " + cacheConfig.getTtl().getSeconds());
//
//        return cacheConfig;
//    }
//
//    @Bean
//    public RedisCacheManager cacheManager() {
//
//        log.info("Info -> Redis Cache Manager");
//
//        RedisCacheManager rcm = RedisCacheManager
//                .builder(this.jedisConnectionFactory())
//                .cacheDefaults(this.cacheConfiguration())
//                .build();
//
//        return rcm;
//    }

}
