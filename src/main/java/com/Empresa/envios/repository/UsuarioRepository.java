package com.Empresa.envios.repository;

//UsuarioRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Empresa.envios.entity.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
 Usuario findByToken(UUID token);
 @Query("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.telefonos")
 List<Usuario> findAllWithTelefonos();
 boolean existsByEmail(String email);
}