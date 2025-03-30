package com.Empresa.envios.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Empresa.envios.entity.Telefono;
import com.Empresa.envios.entity.Usuario;
import com.Empresa.envios.exception.EmailAlreadyExistsException;
import com.Empresa.envios.mapper.UsuarioMapper;
import com.Empresa.envios.repository.UsuarioRepository;
import com.Empresa.envios.vo.TelefonoVO;
import com.Empresa.envios.vo.UsuarioVO;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Transactional
    public void guardarUsuarioConTelefonos(UsuarioVO usuarioVO) throws EmailAlreadyExistsException {
        // Verificar si el correo electrónico ya está registrado
        if (usuarioRepository.existsByEmail(usuarioVO.getEmail())) {
            throw new EmailAlreadyExistsException("El correo electrónico ya está registrado.");
        }

        // Mapear UsuarioVO a Usuario
        Usuario usuario = usuarioMapper.mapToUsuario(usuarioVO);
        
        LocalDateTime ahora = LocalDateTime.now();
        Date ultimoLoginDate = Date.from(ahora.atZone(ZoneId.systemDefault()).toInstant());

            usuario.setFechaCreacion(new Date()); // Establecer la fecha de creación
            usuario.setFechaActualizacion(new Date()); // Puede establecerse cuando se actualice el usuario
            usuario.setUltimoLogin(ultimoLoginDate); // Inicialmente puede estar vacío
            UUID uuid = UUID.randomUUID();
            usuario.setToken(uuid); // Asignar el token generado
            // Mapear los teléfonos de UsuarioVO a Telefono y asociarlos al usuario
            for (TelefonoVO telefonoVO : usuarioVO.getTelefonos()) {
                Telefono telefono = new Telefono();
                telefono.setNumero(telefonoVO.getNumero());
                telefono.setCodCiudad(telefonoVO.getCodCiudad());
                telefono.setCountryCode(telefonoVO.getCountryCode());

                usuario.addTelefono(telefono); // Agregar teléfono al usuario
            }

            // Guardar el usuario (y sus teléfonos asociados)
            usuarioRepository.save(usuario);
    }
}