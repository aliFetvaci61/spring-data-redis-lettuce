package com.alifetvaci.springdataredislettuce.redis.service;

import com.alifetvaci.springdataredislettuce.redis.model.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl extends RedisService<Session> {

    public SessionServiceImpl(RedisTemplate<String, Session> redisTemplate) {
        super(redisTemplate);
    }

}
