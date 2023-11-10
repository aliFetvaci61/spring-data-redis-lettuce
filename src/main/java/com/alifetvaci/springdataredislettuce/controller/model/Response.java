package com.alifetvaci.springdataredislettuce.controller.model;


import com.alifetvaci.springdataredislettuce.redis.model.Session;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private boolean success;

    private Session session;

}
