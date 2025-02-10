package com.tuspring.gestortareas.repository;

import com.tuspring.gestortareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Buscar todas las tareas de un proyecto específico
    List<Tarea> findByProyectoId(Long proyectoId);
}