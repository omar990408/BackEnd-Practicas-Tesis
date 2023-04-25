package com.sistema.examenes.controller;

import com.sistema.examenes.auth.AuthenticationResponse;
import com.sistema.examenes.configuration.JwtUtil;
import com.sistema.examenes.model.Rol;
import com.sistema.examenes.model.Usuario;
import com.sistema.examenes.model.UsuarioRol;
import com.sistema.examenes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/")
    public ResponseEntity<AuthenticationResponse> guardarUsuario(@RequestBody Usuario usuario) throws Exception {
        usuario.setPerfil("default.png");
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Set<UsuarioRol> roles = new HashSet<>();
        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        roles.add(usuarioRol);

        usuarioService.guardarUsuario(usuario, roles);
        var jwtToken = jwtUtil.generateToken(usuario);
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .token(jwtToken)
                .build());
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable String username) {
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable Long usuarioId) {
        usuarioService.eliminarUsuario(usuarioId);
    }
}

