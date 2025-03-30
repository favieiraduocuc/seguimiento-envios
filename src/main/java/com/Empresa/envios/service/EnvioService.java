package com.Empresa.envios.service;

import org.springframework.beans.factory.annotation.Autowired;
//import com.fvieira.envios.dto.EnvioDTO;
//import com.fvieira.envios.entity.Envio;
//import com.fvieira.envios.exception.RecursoNoEncontradoException;
//import com.fvieira.envios.repository.EnvioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Empresa.DTO.EnvioDTO;
import com.Empresa.envios.entity.Envio;
import com.Empresa.envios.exception.RecursoNoEncontradoException;
import com.Empresa.envios.repository.EnvioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<EnvioDTO> obtenerTodos() {
        return envioRepository.findAll().stream()
                .map(envio -> new EnvioDTO(envio.getCodigo(), envio.getEstado(), envio.getUbicacion()))
                .collect(Collectors.toList());
    }

    public EnvioDTO obtenerPorId(Long id) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Envío no encontrado con ID: " + id));
        return new EnvioDTO(envio.getCodigo(), envio.getEstado(), envio.getUbicacion());
    }
}
