// Usuario.java
package com.Empresa.envios.entity;

import javax.persistence.*;

import com.Empresa.envios.vo.TelefonoVO;
import com.Empresa.envios.vo.UsuarioVO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ultimo_login")
    private Date ultimoLogin;
    
    @Column(name = "activo") // Default 0
    private int activo;

    @Column(unique = true, columnDefinition = "BINARY(16)")
    private UUID token;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Telefono> telefonos = new ArrayList<>();
    // Getters y setters
    // Métodos para agregar y eliminar teléfonos
    public void addTelefono(Telefono telefono) {
        telefonos.add(telefono);
        telefono.setUsuario(this);
    }

    public void removeTelefono(Telefono telefono) {
        telefonos.remove(telefono);
        telefono.setUsuario(null);
    }
}
