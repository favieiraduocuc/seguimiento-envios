package com.Empresa.envios.repository;

//TelefonoRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.Empresa.envios.entity.Telefono;

public interface TelefonoRepository extends JpaRepository<Telefono, Integer> {
}