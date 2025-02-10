package com.tuspring.gestortareas.controller;

import com.tuspring.gestortareas.model.Tarea;
import com.tuspring.gestortareas.service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    // Listar todas las tareas de un proyecto por id
    @GetMapping("/proyecto/{proyectoId}")
    public List<Tarea> listarTareasPorProyecto(@PathVariable Long proyectoId) {
        return tareaService.listarPorProyecto(proyectoId);
    }

    // Obtener una tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaService.obtenerPorId(id);
        return tarea.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva tarea
    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaService.guardar(tarea);
    }

    // Actualizar una tarea ya existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada) {
        try {
            Tarea tarea = tareaService.actualizar(id, tareaActualizada);
            return ResponseEntity.ok(tarea);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
