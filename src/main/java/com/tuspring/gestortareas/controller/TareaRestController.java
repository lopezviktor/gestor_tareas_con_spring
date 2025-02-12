package com.tuspring.gestortareas.controller;

import com.tuspring.gestortareas.model.EstadoTarea;
import com.tuspring.gestortareas.model.Proyecto;
import com.tuspring.gestortareas.model.Tarea;
import com.tuspring.gestortareas.service.ProyectoService;
import com.tuspring.gestortareas.service.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaRestController {

    private final TareaService tareaService;
    private final ProyectoService proyectoService;

    public TareaRestController(TareaService tareaService, ProyectoService proyectoService) {
        this.tareaService = tareaService;
        this.proyectoService = proyectoService;
    }

    // Listar todas las tareas
    @GetMapping
    public List<Tarea> obtenerTareas() {
        return tareaService.obtenerTodasLasTareas();
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
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        if (tarea.getProyecto() == null || tarea.getProyecto().getId() == null) {
            return ResponseEntity.badRequest().body(null); // Si no se proporciona un proyecto, devuelve un error
        }

        // Buscar el proyecto en la base de datos
        Proyecto proyecto = proyectoService.obtenerProyectoPorId(tarea.getProyecto().getId());

        if (proyecto == null) {
            return ResponseEntity.badRequest().body(null); // Si el proyecto no existe, devuelve un error
        }

        // Asigna el proyecto a la tarea
        tarea.setProyecto(proyecto);

        // Guardar la tarea
        Tarea nuevaTarea = tareaService.guardar(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
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

    // Obtener tareas por estado
    @GetMapping("/estado/{estado}")
    public List<Tarea> obtenerTareasPorEstado(@PathVariable EstadoTarea estado) {
        return tareaService.listarPorEstado(estado);
    }

    // Obtener tareas de un proyecto en un estado específico
    @GetMapping("/proyecto/{proyectoId}/estado/{estado}")
    public List<Tarea> obtenerTareasPorProyectoYEstado(@PathVariable Long proyectoId, @PathVariable EstadoTarea estado) {
        return tareaService.listarPorProyectoYEstado(proyectoId, estado);
    }

}
