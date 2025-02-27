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

        long totalProyectos = proyectoService.contarProyectos();

        long totalTareas = tareaService.contarTareas();

        long tareasCompletadas = tareaService.contarTareasCompletadas();

        model.addAttribute("totalProyectos", totalProyectos);
        model.addAttribute("totalTareas", totalTareas);
        model.addAttribute("tareasCompletadas", tareasCompletadas);
        return "index";
    }
}
