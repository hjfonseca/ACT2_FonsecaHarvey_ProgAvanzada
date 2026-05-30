package com.estudiante.proyecto2.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface HardwareAiServiceHF {

    @SystemMessage("Genera un resumen técnico y ejecutivo del inventario de ESPE-Tech basado en las métricas analíticas provistas.")
    String generarResumenInventario(@UserMessage String datosAnaliticos);
}