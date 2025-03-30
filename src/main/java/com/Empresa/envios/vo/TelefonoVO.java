package com.Empresa.envios.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefonoVO {

    private int idTelefono;
    private int numero;
    private int codCiudad;
    private int countryCode;
    @JsonBackReference
    private UsuarioVO usuario;
    
}