package com.Empresa.envios.mapper;

import org.springframework.stereotype.Component;

import com.Empresa.envios.entity.Telefono;
import com.Empresa.envios.vo.TelefonoVO;

@Component
public class TelefonoMapper {

    public TelefonoVO mapToTelefonoVO(Telefono telefono) {
        if (telefono == null) {
            return null;
        }

        return TelefonoVO.builder()
                .idTelefono(telefono.getIdTelefono())
                .numero(telefono.getNumero())
                .codCiudad(telefono.getCodCiudad())
                .countryCode(telefono.getCountryCode())
                // Otros atributos de TelefonoVO que puedas necesitar mapear
                .build();
    }
}