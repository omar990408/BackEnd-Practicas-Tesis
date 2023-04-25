package com.sistema.examenes.repository;

import com.sistema.examenes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  public Usuario findByUsername(String username);

}
