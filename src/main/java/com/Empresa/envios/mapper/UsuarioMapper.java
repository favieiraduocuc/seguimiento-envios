package com.Empresa.envios.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Empresa.envios.entity.Telefono;
import com.Empresa.envios.entity.Usuario;
import com.Empresa.envios.vo.TelefonoVO;
import com.Empresa.envios.vo.UsuarioVO;

@Component
public class UsuarioMapper {

    @Autowired
    private TelefonoMapper telefonoMapper;

    public Usuario mapToUsuario(UsuarioVO usuarioVO) {
        if (usuarioVO == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioVO.getIdUsuario());
        usuario.setNombre(usuarioVO.getNombre());
        usuario.setEmail(usuarioVO.getEmail());
        usuario.setPassword(usuarioVO.getPassword());
        usuario.setFechaCreacion(usuarioVO.getFechaCreacion());
        usuario.setFechaActualizacion(usuarioVO.getFechaActualizacion());
        usuario.setUltimoLogin(usuarioVO.getUltimoLogin());
        usuario.setActivo(usuarioVO.getActivo());
        usuario.setToken(usuarioVO.getToken());

        // No se mapea la lista de teléfonos aquí, ya que es un caso más complejo

        return usuario;
    }

    public UsuarioVO mapToUsuarioVO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioVO usuarioVO = UsuarioVO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .fechaCreacion(usuario.getFechaCreacion())
                .fechaActualizacion(usuario.getFechaActualizacion())
                .ultimoLogin(usuario.getUltimoLogin())
                .activo(usuario.getActivo())
                .token(usuario.getToken())
                .build();

        // Mapeo de la lista de teléfonos si existe
        if (usuario.getTelefonos() != null) {
            List<TelefonoVO> telefonosVO = usuario.getTelefonos().stream()
                    .map(telefonoMapper::mapToTelefonoVO)
                    .collect(Collectors.toList());
            usuarioVO.setTelefonos(telefonosVO);
        } else {
            usuarioVO.setTelefonos(new ArrayList<>()); // Inicializa la lista si es null
        }

        return usuarioVO;
    }
}