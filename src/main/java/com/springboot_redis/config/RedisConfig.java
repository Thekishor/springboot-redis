package com.springboot_redis.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

   @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory){

       RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
               .entryTtl(Duration.ofMinutes(10))
               .disableCachingNullValues()
               .enableTimeToIdle()
               .serializeValuesWith(RedisSerializationContext.SerializationPair
                       .fromSerializer(new Jackson2JsonRedisSerializer<>(Object.class)));

       return RedisCacheManager
               .builder(connectionFactory)
               .cacheDefaults(cacheConfiguration)
               .build();
   }
}
