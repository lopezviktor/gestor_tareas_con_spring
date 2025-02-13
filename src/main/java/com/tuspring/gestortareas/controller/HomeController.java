package com.tuspring.gestortareas.controller;

import com.tuspring.gestortareas.service.ProyectoService;
import com.tuspring.gestortareas.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProyectoService proyectoService;
    private final TareaService tareaService;

    public HomeController(ProyectoService proyectoService, TareaService tareaService) {
        this.proyectoService = proyectoService;
        this.tareaService = tareaService;
    }

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        // Obtiene el total de proyectos
        long totalProyectos = proyectoService.contarProyectos();
        // Obtiene el total de tareas
        long totalTareas = tareaService.contarTareas();
        // Obtiene el total de tareas completadas
        long tareasCompletadas = tareaService.contarTareasCompletadas();
        // Añade los valores al modelo para Thymeleaf
        model.addAttribute("totalProyectos", totalProyectos);
        model.addAttribute("totalTareas", totalTareas);
        model.addAttribute("tareasCompletadas", tareasCompletadas);
        return "index";
    }
}
