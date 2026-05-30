package com.estudiante.proyecto2.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepositoryHF extends JpaRepository<HardwareEntityHF, Long> {
}