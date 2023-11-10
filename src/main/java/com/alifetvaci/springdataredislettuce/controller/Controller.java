package com.alifetvaci.springdataredislettuce.controller;


import com.alifetvaci.springdataredislettuce.controller.model.Request;
import com.alifetvaci.springdataredislettuce.controller.model.Response;
import com.alifetvaci.springdataredislettuce.redis.model.Session;
import com.alifetvaci.springdataredislettuce.redis.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {

    private final RedisService<Session> sessionRedisService;

    @GetMapping("/session")
    public ResponseEntity<Response> getSessionInfo(@RequestHeader(value = "user-session") String userSession) {

        Session session = sessionRedisService.getValue(userSession);

        if(Objects.isNull(session)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Response response = Response.builder().session(session).success(true).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/session")
    public ResponseEntity<Response> addSessionInfo(@RequestBody Request request) {

        Session session = Session.builder().sessionId(request.getSessionId()).userId(request.getUserId()).build();

        sessionRedisService.putValue(session.getSessionId(), TimeUnit.MINUTES, 3, session);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
