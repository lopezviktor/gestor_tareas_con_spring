package com.tuspring.gestortareas.repository;

import com.tuspring.gestortareas.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    // Añadir metodos
}