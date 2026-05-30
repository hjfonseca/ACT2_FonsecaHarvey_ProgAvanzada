package com.estudiante.proyecto2.dto;

import com.estudiante.proyecto2.persistence.HardwareEntityHF;
import java.math.BigDecimal;

public class CategoriaReporteDTO_HF {
    private String categoria;
    private BigDecimal valorTotal;
    private BigDecimal precioPromedio;
    private HardwareEntityHF equipoMasCaro;

    public CategoriaReporteDTO_HF() {
    }

    public CategoriaReporteDTO_HF(String categoria, BigDecimal valorTotal, BigDecimal precioPromedio, HardwareEntityHF equipoMasCaro) {
        this.categoria = categoria;
        this.valorTotal = valorTotal;
        this.precioPromedio = precioPromedio;
        this.equipoMasCaro = equipoMasCaro;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public BigDecimal getPrecioPromedio() { return precioPromedio; }
    public void setPrecioPromedio(BigDecimal precioPromedio) { this.precioPromedio = precioPromedio; }

    public HardwareEntityHF getEquipoMasCaro() { return equipoMasCaro; }
    public void setEquipoMasCaro(HardwareEntityHF equipoMasCaro) { this.equipoMasCaro = equipoMasCaro; }
}