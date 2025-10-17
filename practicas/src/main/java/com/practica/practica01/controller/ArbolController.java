package com.practica.practica01.controller;

/**
 *
 * @author darry
 */

import com.practica.practica01.entity.Arbol;
import com.practica.practica01.service.ArbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/arboles")
public class ArbolController {

    @Autowired
    private ArbolService arbolService;

    // LISTAR todos los árboles
    @GetMapping
    public String listarArboles(Model model) {
        List<Arbol> arboles = arbolService.getAllArboles();
        model.addAttribute("arboles", arboles);
        return "arbol/listar"; // Esto apuntará a templates/arbol/listar.html
    }

    // Mostrar formulario para CREAR nuevo árbol
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "arbol/formulario";
    }

    // GUARDAR nuevo árbol
    @PostMapping("/guardar")
    public String guardarArbol(@ModelAttribute Arbol arbol) {
        arbolService.saveArbol(arbol);
        return "redirect:/arboles";
    }

    // Mostrar formulario para EDITAR árbol
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Arbol arbol = arbolService.getArbolById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de árbol inválido: " + id));
        model.addAttribute("arbol", arbol);
        return "arbol/formulario";
    }

    // ELIMINAR árbol
    @GetMapping("/eliminar/{id}")
    public String eliminarArbol(@PathVariable Long id) {
        arbolService.deleteArbol(id);
        return "redirect:/arboles";
    }
}