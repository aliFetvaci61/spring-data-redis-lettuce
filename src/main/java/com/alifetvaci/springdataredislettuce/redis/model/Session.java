package com.alifetvaci.springdataredislettuce.redis.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session implements Serializable {

    @Serial
    private static final long serialVersionUID = -4629854556123464081L;

    private String userId;
    private String sessionId;

}
