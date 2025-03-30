package com.Empresa.envios.entity;


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
@Table(name = "envios")
public class Envio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Long id;

    @Column(name = "codigo", unique = true)
    private String codigo;

    @Column(name = "estado")
    private String estado;

    @Column(name = "ubicacion")
    private String ubicacion;
}
