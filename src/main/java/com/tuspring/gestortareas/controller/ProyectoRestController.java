package com.tuspring.gestortareas.controller;

import com.tuspring.gestortareas.model.EstadoProyecto;
import com.tuspring.gestortareas.model.Proyecto;
import com.tuspring.gestortareas.service.ProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoRestController {

    private final ProyectoService proyectoService;

    public ProyectoRestController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    // Listar todos los proyectos
    @GetMapping
    public List<Proyecto> listarProyectos() {
        return proyectoService.listarTodos();
    }

    // Obtener un proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerProyecto(@PathVariable Long id) {
        Optional<Proyecto> proyecto = proyectoService.obtenerPorId(id);
        return proyecto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo proyecto
    @PostMapping
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoService.guardar(proyecto);
    }

    // Actualizar un proyecto existente
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyectoActualizado) {
        try {
            Proyecto proyecto = proyectoService.actualizar(id, proyectoActualizado);
            return ResponseEntity.ok(proyecto);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id){
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener proyectos por estado
    @GetMapping("/estado/{estado}")
    @ResponseBody
    public List<Proyecto> obtenerProyectosPorEstado(@PathVariable EstadoProyecto estado){
        return proyectoService.listarPorEstado(estado);
    }

    // Buscar proyectos por nombre (filtro de busqueda)
    @GetMapping("/buscar")
    @ResponseBody
    public List<Proyecto> buscarProyectosPorNombre(@RequestParam String nombre) {
        return proyectoService.buscarPorNombre(nombre);
    }
}
