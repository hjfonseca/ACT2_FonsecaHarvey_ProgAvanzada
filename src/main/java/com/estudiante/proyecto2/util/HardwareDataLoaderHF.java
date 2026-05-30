package com.estudiante.proyecto2.util;

import com.estudiante.proyecto2.persistence.HardwareEntityHF;
import com.estudiante.proyecto2.persistence.HardwareRepositoryHF;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class HardwareDataLoaderHF implements CommandLineRunner {

    private final HardwareRepositoryHF hardwareRepositoryHF;

    public HardwareDataLoaderHF(HardwareRepositoryHF hardwareRepositoryHF) {
        this.hardwareRepositoryHF = hardwareRepositoryHF;
    }

    @Override
    public void run(String... args) throws Exception {
        if (hardwareRepositoryHF.count() == 0) {
            System.out.println("=== SYSTEM HF: Inicializando generación automática de 10,000 registros ===");

            List<HardwareEntityHF> loteHardware = new ArrayList<>();
            Random random = new Random();

            String[] categorias = {"Laptop", "PC", "Servidor"};
            String[] marcas = {"Dell", "HP", "Lenovo", "Asus", "Apple", "Supermicro"};
            String[] estados = {"ACTIVO", "DEBAJA"};

            for (int i = 1; i <= 10000; i++) {
                String categoria = categorias[random.nextInt(categorias.length)];
                String marca = marcas[random.nextInt(marcas.length)];
                String modelo = marca + " " + categoria + " Mod-" + (random.nextInt(900) + 100) + " v" + i;

                double precioBase = categoria.equals("Servidor") ? 3000.0 : (categoria.equals("Laptop") ? 950.0 : 650.0);
                double precioFinal = precioBase + (random.nextDouble() * 800);
                BigDecimal precio = BigDecimal.valueOf(precioFinal).setScale(2, RoundingMode.HALF_UP);

                LocalDate fechaCompra = LocalDate.now().minusDays(random.nextInt(2920));
                String estado = estados[random.nextInt(estados.length)];

                loteHardware.add(new HardwareEntityHF(modelo, categoria, precio, fechaCompra, estado));

                if (i % 1000 == 0) {
                    hardwareRepositoryHF.saveAll(loteHardware);
                    loteHardware.clear();
                }
            }

            System.out.println("SYSTEM HF: Se hace la insercion masiva exitosamente");
        } else {
            System.out.println("SYSTEM HF: Registros existentes detectados en la tabla.");
        }
    }
}
