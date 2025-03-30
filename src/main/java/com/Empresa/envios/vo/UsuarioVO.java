package com.Empresa.envios.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioVO {

    private int idUsuario;
    private String nombre;
    private String email;
    private String password;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private Date ultimoLogin;
    private int activo;
    private UUID token;
    private List<TelefonoVO> telefonos;
    
}