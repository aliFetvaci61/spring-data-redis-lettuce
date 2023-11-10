package com.alifetvaci.springdataredislettuce.redis.configuration;


import com.alifetvaci.springdataredislettuce.redis.model.Session;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    private final LettuceConnectionFactory connectionFactory;

    public RedisConfiguration(LettuceConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;

    }

    @Bean
    public RedisTemplate<String, Session> sessionRedisTemplate() {

        final RedisTemplate<String, Session> redisTemplate = new RedisTemplate<>();
        final var jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Session.class);
        final ObjectMapper om = new ObjectMapper();

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
