package com.estudiante.proyecto2.service;

import com.estudiante.proyecto2.dto.CategoriaReporteDTO_HF;
import com.estudiante.proyecto2.dto.InventarioResumenDTO_HF;
import com.estudiante.proyecto2.persistence.HardwareEntityHF;
import com.estudiante.proyecto2.persistence.HardwareRepositoryHF;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImplHF implements HardwareServiceHF {

    private final HardwareRepositoryHF hardwareRepositoryHF;

    public HardwareServiceImplHF(HardwareRepositoryHF hardwareRepositoryHF) {
        this.hardwareRepositoryHF = hardwareRepositoryHF;
    }

    @Override
    public InventarioResumenDTO_HF procesarImperativo() {
        long startTime = System.currentTimeMillis();

        List<HardwareEntityHF> todosLosEquipos = hardwareRepositoryHF.findAll();
        LocalDate limiteFecha = LocalDate.now().minusYears(5);

        Map<String, List<HardwareEntityHF>> gruposPorCategoria = new HashMap<>();

        for (HardwareEntityHF equipo : todosLosEquipos) {
            if (equipo.getEstado().equals("ACTIVO") && equipo.getFechaCompra().isAfter(limiteFecha)) {
                String cat = equipo.getCategoria();
                if (!gruposPorCategoria.containsKey(cat)) {
                    gruposPorCategoria.put(cat, new ArrayList<>());
                }
                gruposPorCategoria.get(cat).add(equipo);
            }
        }

        List<CategoriaReporteDTO_HF> listaReportes = new ArrayList<>();

        for (Map.Entry<String, List<HardwareEntityHF>> entrada : gruposPorCategoria.entrySet()) {
            String categoria = entrada.getKey();
            List<HardwareEntityHF> equiposCat = entrada.getValue();

            BigDecimal valorTotal = BigDecimal.ZERO;
            HardwareEntityHF equipoMasCaro = null;

            for (HardwareEntityHF e : equiposCat) {
                valorTotal = valorTotal.add(e.getPrecio());

                if (equipoMasCaro == null || e.getPrecio().compareTo(equipoMasCaro.getPrecio()) > 0) {
                    equipoMasCaro = e;
                }
            }

            BigDecimal promedio = BigDecimal.ZERO;
            if (!equiposCat.isEmpty()) {
                promedio = valorTotal.divide(BigDecimal.valueOf(equiposCat.size()), 2, RoundingMode.HALF_UP);
            }

            listaReportes.add(new CategoriaReporteDTO_HF(categoria, valorTotal, promedio, equipoMasCaro));
        }

        long endTime = System.currentTimeMillis();
        return new InventarioResumenDTO_HF("IMPERATIVO", listaReportes, (endTime - startTime));
    }

    @Override
    public InventarioResumenDTO_HF procesarFuncional() {
        long startTime = System.currentTimeMillis();

        List<HardwareEntityHF> todosLosEquipos = hardwareRepositoryHF.findAll();
        LocalDate limiteFecha = LocalDate.now().minusYears(5);

        Map<String, List<HardwareEntityHF>> agrupaMap = todosLosEquipos.stream()
                .filter(e -> "ACTIVO".equals(e.getEstado()))
                .filter(e -> e.getFechaCompra().isAfter(limiteFecha))
                .collect(Collectors.groupingBy(HardwareEntityHF::getCategoria));

        List<CategoriaReporteDTO_HF> listaReportes = agrupaMap.entrySet().stream()
                .map(entrada -> {
                    String categoria = entrada.getKey();
                    List<HardwareEntityHF> listaCat = entrada.getValue();

                    BigDecimal valorTotal = listaCat.stream()
                            .map(HardwareEntityHF::getPrecio)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    BigDecimal promedio = listaCat.isEmpty() ? BigDecimal.ZERO :
                            valorTotal.divide(BigDecimal.valueOf(listaCat.size()), 2, RoundingMode.HALF_UP);

                    HardwareEntityHF masCaro = listaCat.stream()
                            .max(Comparator.comparing(HardwareEntityHF::getPrecio))
                            .orElse(null);

                    return new CategoriaReporteDTO_HF(categoria, valorTotal, promedio, masCaro);
                })
                .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        return new InventarioResumenDTO_HF("FUNCIONAL_STREAMS", listaReportes, (endTime - startTime));
    }
}
