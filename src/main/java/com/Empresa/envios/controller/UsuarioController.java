package com.Empresa.envios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Empresa.envios.entity.Usuario;
import com.Empresa.envios.exception.EmailAlreadyExistsException;
import com.Empresa.envios.mapper.UsuarioMapper;
import com.Empresa.envios.repository.UsuarioRepository;
import com.Empresa.envios.service.UsuarioService;
import com.Empresa.envios.vo.TelefonoVO;
import com.Empresa.envios.vo.UsuarioVO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;
    
    @PostMapping("/crearUsuarioConTelefono")
    public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioVO usuarioVO) {
        // Validacion del formato de correo
        if (!isValidEmailFormat(usuarioVO.getEmail())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Formato de correo electrónico no válido. Debe seguir el formato xxxx@xxx.xx");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        try {
            usuarioService.guardarUsuarioConTelefonos(usuarioVO);
            // Devolucion con exito del JSON
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("mensaje", "Usuario creado con éxito.");
            return ResponseEntity.ok(successResponse);
        } catch (EmailAlreadyExistsException e) {
            // Correo electrónico ya existe, nos devuelve el mensaje respectivo
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            // Excepciones JSON ERROR
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    @GetMapping("/listarUsuariosYTelefonos")
    public List<UsuarioVO> listarUsuariosYTelefonos() {
        List<Usuario> usuariosConTelefonos = usuarioRepository.findAllWithTelefonos();

        // Mapea la lista de usuarios a UsuarioVO usando UsuarioMapper
        List<UsuarioVO> usuariosVO = usuariosConTelefonos.stream()
                .map(usuario -> {
                    UsuarioVO usuarioVO = usuarioMapper.mapToUsuarioVO(usuario);
                    if (usuarioVO != null) {
                        List<TelefonoVO> telefonosVO = usuarioVO.getTelefonos();
                        if (telefonosVO == null) {
                            System.out.println("UsuarioVO " + usuarioVO.getIdUsuario() + " tiene telefonosVO null");
                        } else {
                            System.out.println("UsuarioVO " + usuarioVO.getIdUsuario() + " tiene " + telefonosVO.size() + " telefonosVO");
                        }
                    } else {
                        System.out.println("Usuario null encontrado en usuariosConTelefonos");
                    }
                    return usuarioVO;
                })
                .collect(Collectors.toList());

        return usuariosVO;
    }

    // Validacion de correo si existe o no
    private boolean isValidEmailFormat(String email) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    

}

