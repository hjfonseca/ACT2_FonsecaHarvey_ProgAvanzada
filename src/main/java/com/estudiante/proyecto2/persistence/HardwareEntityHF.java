package com.estudiante.proyecto2.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "espe_tech_hardware")
public class HardwareEntityHF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String modelo;

    @Column(nullable = false, length = 50)
    private String categoria; // Laptop, PC, Servidor

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @Column(nullable = false, length = 15)
    private String estado; // ACTIVO, DEBAJA

    public HardwareEntityHF() {
    }
    public HardwareEntityHF(String modelo, String category, BigDecimal precio, LocalDate fechaCompra, String estado) {
        this.modelo = modelo;
        this.categoria = category;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}