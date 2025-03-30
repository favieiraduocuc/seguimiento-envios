package com.Empresa.envios.controller;

import com.Empresa.DTO.EnvioDTO;
import com.Empresa.envios.service.EnvioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<EnvioDTO> listarEnvios() {
        return envioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public EnvioDTO obtenerEnvio(@PathVariable Long id) {
        return envioService.obtenerPorId(id);
    }
}

