package com.tuspring.gestortareas.controller;

import com.tuspring.gestortareas.model.Tarea;
import com.tuspring.gestortareas.service.ProyectoService;
import com.tuspring.gestortareas.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    private TareaService tareaService;

    private ProyectoService proyectoService;

    public TareaController(TareaService tareaService, ProyectoService proyectoService) {
        this.tareaService = tareaService;
        this.proyectoService = proyectoService;
    }

    // Mostrar lista de tareas en Thymeleaf
    @GetMapping
    public String listarTareas(Model model) {
        List<Tarea> tareas = tareaService.obtenerTodasLasTareas();
        model.addAttribute("tareas", tareas);
        return "tareas/index"; // Carga tareas/index.html
    }

    // Mostrar formulario para crear una nueva tarea
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("proyectos", proyectoService.listarTodos()); // Para seleccionar un proyecto
        return "tareas/crear";
    }

    // Guardar una nueva tarea desde el formulario Thymeleaf
    @PostMapping("/guardar")
    public String guardarTarea(@ModelAttribute Tarea tarea) {
        tareaService.guardar(tarea);
        return "redirect:/tareas";
    }

    // Mostrar formulario de edición de tarea
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Tarea> tarea = tareaService.obtenerPorId(id);
        if (tarea.isPresent()) {
            model.addAttribute("tarea", tarea.get());
            model.addAttribute("proyectos", proyectoService.listarTodos()); // Para seleccionar proyecto
            return "tareas/editar";
        } else {
            return "redirect:/tareas";
        }
    }

    // Actualizar una tarea desde el formulario Thymeleaf
    @PostMapping("/actualizar/{id}")
    public String actualizarTarea(@PathVariable Long id, @ModelAttribute Tarea tareaActualizada) {
        tareaService.actualizar(id, tareaActualizada);
        return "redirect:/tareas";
    }

    // Eliminar una tarea desde Thymeleaf
    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaService.eliminar(id);
        return "redirect:/tareas";
    }


}
