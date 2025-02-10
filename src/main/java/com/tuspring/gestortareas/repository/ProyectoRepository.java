package com.tuspring.gestortareas.repository;

import com.tuspring.gestortareas.model.EstadoProyecto;
import com.tuspring.gestortareas.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    // Buscar proyectos por estado
    List<Proyecto> findByEstado(EstadoProyecto estado);

    // Buscar proyectos que contengan alguna palabra clave
    List<Proyecto> findByNombreContainingIgnoreCase(String nombre);
}