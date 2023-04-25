package com.sistema.examenes.auth;

import com.sistema.examenes.configuration.JwtUtil;
import com.sistema.examenes.model.Rol;
import com.sistema.examenes.model.Usuario;
import com.sistema.examenes.model.UsuarioRol;
import com.sistema.examenes.repository.UsuarioRepository;
import com.sistema.examenes.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest  request) throws Exception {
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = usuarioRepository.findByUsername(request.getUsername());
        if (user != null) {
            var jwtToken = jwtUtil.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }   else {
            throw new RuntimeException("Invalid username/password");
        }
    }
}
