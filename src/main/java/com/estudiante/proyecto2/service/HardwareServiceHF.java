package com.estudiante.proyecto2.service;

import com.estudiante.proyecto2.dto.InventarioResumenDTO_HF;

public interface HardwareServiceHF {
    InventarioResumenDTO_HF procesarImperativo();
    InventarioResumenDTO_HF procesarFuncional();
}