package com.sistema.examenes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
