package com.tuspring.gestortareas.service;

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

    // Listar lista de tareas por id de proyecto
    public List<Tarea> listarPorProyecto(Long proyectoId) {
        return tareaRepository.findByProyectoId(proyectoId);
    }

    // Listar tarea por id
    public Optional<Tarea> obtenerPorId(Long id){
        return tareaRepository.findById(id);
    }

    // Guardar nueva tarea
    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Eliminar una tarea
    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}
