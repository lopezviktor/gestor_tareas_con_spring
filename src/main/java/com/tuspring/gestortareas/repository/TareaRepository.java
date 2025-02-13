package com.tuspring.gestortareas.repository;

import com.tuspring.gestortareas.model.EstadoTarea;
import com.tuspring.gestortareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Buscar todas las tareas de un proyecto específico
    List<Tarea> findByProyectoId(Long proyectoId);

    // Buscar tareas por estado
    List<Tarea> findByEstado(EstadoTarea estado);

    // Buscar tareas de un proyecto en algun estado especifico
    List<Tarea> findByProyectoIdAndEstado(Long proyectoId, EstadoTarea estado);

    long countByEstado(EstadoTarea estado);


}