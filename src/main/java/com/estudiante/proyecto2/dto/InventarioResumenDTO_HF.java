package com.estudiante.proyecto2.dto;

import java.util.List;

public class InventarioResumenDTO_HF {
    private String paradigma;
    private List<CategoriaReporteDTO_HF> reportesPorCategoria;
    private long tiempoEjecucionMs;

    public InventarioResumenDTO_HF() {
    }

    public InventarioResumenDTO_HF(String paradigma, List<CategoriaReporteDTO_HF> reportesPorCategoria, long tiempoEjecucionMs) {
        this.paradigma = paradigma;
        this.reportesPorCategoria = reportesPorCategoria;
        this.tiempoEjecucionMs = tiempoEjecucionMs;
    }

    // Getters y Setters
    public String getParadigma() { return paradigma; }
    public void setParadigma(String paradigma) { this.paradigma = paradigma; }

    public List<CategoriaReporteDTO_HF> getReportesPorCategoria() { return reportesPorCategoria; }
    public void setReportesPorCategoria(List<CategoriaReporteDTO_HF> reportesPorCategoria) { this.reportesPorCategoria = reportesPorCategoria; }

    public long getTiempoEjecucionMs() { return tiempoEjecucionMs; }
    public void setTiempoEjecucionMs(long tiempoEjecucionMs) { this.tiempoEjecucionMs = tiempoEjecucionMs; }
}