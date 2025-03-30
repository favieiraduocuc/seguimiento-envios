// Telefono.java
package com.Empresa.envios.entity;

import javax.persistence.*;

import com.Empresa.envios.vo.TelefonoVO;
import com.Empresa.envios.vo.UsuarioVO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "telefono") 
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefono")
    private int idTelefono;

    @Column(name = "numero")
    private int numero;

    @Column(name = "cod_ciudad")
    private int codCiudad;

    @Column(name = "country_code")
    private int countryCode;

    @ManyToOne
    @JoinColumn(name = "id_usuario_telefono")
    @JsonBackReference
    private Usuario usuario;
    
}
