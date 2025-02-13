package com.tuspring.gestortareas.controller;

import com.tuspring.gestortareas.model.EstadoProyecto;
import com.tuspring.gestortareas.model.Proyecto;
import com.tuspring.gestortareas.service.ProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    // Mostrar la lista de proyectos en Thymeleaf
    @GetMapping
    public String listarProyectos(Model model) {
        List<Proyecto> proyectos = proyectoService.listarTodos();
        model.addAttribute("proyectos", proyectos);
        return "proyectos/index"; // Carga index.html
    }

    // Mostrar un formulario para crear un nuevo proyecto
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyectos/crear"; // Carga crear.html
    }

    // Guardar un nuevo proyecto desde el formulario
    @PostMapping("/guardar")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardar(proyecto);
        return "redirect:/proyectos"; // Redirige a la lista después de guardar
    }

    // Mostrar formulario de edición de proyecto
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Proyecto> proyecto = proyectoService.obtenerPorId(id);
        if (proyecto.isPresent()) {
            model.addAttribute("proyecto", proyecto.get());
            return "proyectos/editar"; // Carga editar.html
        } else {
            return "redirect:/proyectos"; // Si no existe, vuelve a la lista
        }
    }

    // Actualizar proyecto desde el formulario de edicion
    @PostMapping("/actualizar/{id}")
    public String actualizarProyecto(@PathVariable Long id, @ModelAttribute Proyecto proyectoActualizado) {
        proyectoService.actualizar(id, proyectoActualizado);
        return "redirect:/proyectos";
    }

    // Eliminar proyecto y redirige a la lista
    @GetMapping("/eliminar/{id}")
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return "redirect:/proyectos";
    }

}
