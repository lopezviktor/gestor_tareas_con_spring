package com.tuspring.gestortareas.service;

import com.tuspring.gestortareas.model.EstadoProyecto;
import com.tuspring.gestortareas.model.Proyecto;
import com.tuspring.gestortareas.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    @Autowired
    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository){
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> obtenerPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto obtenerProyectoPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

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

    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }

    public List<Proyecto> listarPorEstado(EstadoProyecto estado) {
        return proyectoRepository.findByEstado(estado);
    }

    public List<Proyecto> buscarPorNombre(String nombre) {
        return proyectoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public long contarProyectos() {
        return proyectoRepository.count();
    }
}
