package com.tuspring.gestortareas.service;

import com.tuspring.gestortareas.model.EstadoTarea;
import com.tuspring.gestortareas.model.Tarea;
import com.tuspring.gestortareas.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> listarPorProyecto(Long proyectoId) {
        return tareaRepository.findByProyectoId(proyectoId);
    }

    public Optional<Tarea> obtenerPorId(Long id){
        return tareaRepository.findById(id);
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizar(Long id, Tarea tareaActualizada) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setTitulo(tareaActualizada.getTitulo());
                    tarea.setDescripcion(tareaActualizada.getDescripcion());
                    tarea.setFechaLimite(tareaActualizada.getFechaLimite());
                    tarea.setEstado(tareaActualizada.getEstado());
                    return tareaRepository.save(tarea);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con el id" + id));
    }

    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }

    public List<Tarea> listarPorEstado(EstadoTarea estado) {
        return tareaRepository.findByEstado(estado);
    }

    public List<Tarea> listarPorProyectoYEstado(Long proyectoId, EstadoTarea estado) {
        return tareaRepository.findByProyectoIdAndEstado(proyectoId, estado);
    }
}
