package com.estudiante.proyecto2.controller;

import com.estudiante.proyecto2.ai.HardwareAiServiceHF;
import com.estudiante.proyecto2.dto.InventarioResumenDTO_HF;
import com.estudiante.proyecto2.dto.CategoriaReporteDTO_HF;
import com.estudiante.proyecto2.service.HardwareServiceHF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hardware-analytics")
public class HardwareControllerHF {

    private final HardwareServiceHF hardwareServiceHF;
    private final HardwareAiServiceHF hardwareAiServiceHF;

    public HardwareControllerHF(HardwareServiceHF hardwareServiceHF, HardwareAiServiceHF hardwareAiServiceHF) {
        this.hardwareServiceHF = hardwareServiceHF;
        this.hardwareAiServiceHF = hardwareAiServiceHF;
    }

    @GetMapping("/imperativo-hf")
    public ResponseEntity<Map<String, Object>> getReporteImperativo() {
        InventarioResumenDTO_HF datos = hardwareServiceHF.procesarImperativo();
        return construirRespuestaConIA(datos);
    }

    @GetMapping("/funcional-hf")
    public ResponseEntity<Map<String, Object>> getReporteFuncional() {
        InventarioResumenDTO_HF datos = hardwareServiceHF.procesarFuncional();
        return construirRespuestaConIA(datos);
    }

    private ResponseEntity<Map<String, Object>> construirRespuestaConIA(InventarioResumenDTO_HF datos) {
        StringBuilder reporteCrudo = new StringBuilder();
        reporteCrudo.append("Métricas del Inventario - Paradigma: ").append(datos.getParadigma()).append("\n");

        for (CategoriaReporteDTO_HF r : datos.getReportesPorCategoria()) {
            reporteCrudo.append("Categoría: ").append(r.getCategoria()).append("\n")
                    .append(" Total: $").append(r.getValorTotal()).append("\n")
                    .append(" Promedio: $").append(r.getPrecioPromedio()).append("\n")
                    .append(" Top Costo: ").append(r.getEquipoMasCaro().getModelo())
                    .append(" ($").append(r.getEquipoMasCaro().getPrecio()).append(")\n\n");
        }

        String resumenIA;
        try {
            resumenIA = hardwareAiServiceHF.generarResumenInventario(reporteCrudo.toString());
        } catch (Exception e) {
            resumenIA = "Nota: El módulo de IA se encuentra en modo evaluación local.";
        }

        Map<String, Object> respuestaFinal = new HashMap<>();
        respuestaFinal.put("datosAnaliticos", datos);
        respuestaFinal.put("resumenInteligenteIA", resumenIA);
        respuestaFinal.put("autor", "Harvey Fonseca");

        return ResponseEntity.ok(respuestaFinal);
    }
}