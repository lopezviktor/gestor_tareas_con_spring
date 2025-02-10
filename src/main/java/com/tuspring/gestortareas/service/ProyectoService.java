package com.tuspring.gestortareas.service;

import com.tuspring.gestortareas.model.Proyecto;
import com.tuspring.gestortareas.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {
    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository){
        this.proyectoRepository = proyectoRepository;
    }

    // Listamos todos los proyectos
    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    // Listamos un proyecto buscado por id
    public Optional<Proyecto> obtenerPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    // Guardamos un nuevo proyecto
    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Actualizamos un proyecto
    public Proyecto actualizar(Long id, Proyecto proyectoActualizado) {
        return proyectoRepository.findById(id)
                .map(proyecto -> {
                    proyecto.setNombre(proyectoActualizado.getNombre());
                    proyecto.setDescripcion(proyectoActualizado.getDescripcion());
                    proyecto.setFechaInicio(proyectoActualizado.getFechaInicio());
                    proyecto.setEstado(proyectoActualizado.getEstado());
                    return proyectoRepository.save(proyecto);
                })
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con el id" + id));
    }

    // Eliminamos un proyecto
    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }
}
