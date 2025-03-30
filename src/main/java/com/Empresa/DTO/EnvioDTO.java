package com.Empresa.DTO;

public class EnvioDTO {
    private String codigo;
    private String estado;
    private String ubicacion;

    public EnvioDTO() {}

    public EnvioDTO(String codigo, String estado, String ubicacion) {
        this.codigo = codigo;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}
