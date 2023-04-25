package com.sistema.examenes.controller;

import com.sistema.examenes.auth.AuthenticationRequest;
import com.sistema.examenes.auth.AuthenticationResponse;
import com.sistema.examenes.auth.AuthenticationService;
import com.sistema.examenes.configuration.JwtUtil;
import com.sistema.examenes.model.JwtRequest;
import com.sistema.examenes.model.JwtResponse;
import com.sistema.examenes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/generate-token")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
