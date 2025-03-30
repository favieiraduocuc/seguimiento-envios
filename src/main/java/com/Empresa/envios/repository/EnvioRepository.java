package com.Empresa.envios.repository;

//import com.fvieira.envios.entity.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Empresa.envios.entity.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
}
