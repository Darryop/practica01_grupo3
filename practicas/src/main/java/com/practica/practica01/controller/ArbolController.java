package com.practica.practica01.controller;

/**
 *
 * @author darry
 */
import com.practica.practica01.entity.Arbol;
import com.practica.practica01.service.ArbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/arboles")
public class ArbolController {

    @Autowired
    private ArbolService arbolService;

    @Value("${app.upload.dir:./src/main/resources/static/images/}")
    private String uploadDir;

    // LISTAR todos los árboles
    @GetMapping
    public String listarArboles(Model model) {
        List<Arbol> arboles = arbolService.getAllArboles();
        model.addAttribute("arboles", arboles);
        return "arbol/listado";
    }

    // Mostrar formulario para CREAR nuevo árbol
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "arbol/modifica";
    }

    // GUARDAR nuevo árbol (CON SUBIDA DE IMAGEN CORREGIDO)
    @PostMapping("/guardar")
    public String guardarArbol(@ModelAttribute Arbol arbol,
            @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile,
            Model model) {
        try {
            System.out.println("Procesando guardar árbol...");
            System.out.println("Archivo recibido: " + (imagenFile != null ? imagenFile.getOriginalFilename() : "null"));

            // Procesar imagen si se subió
            if (imagenFile != null && !imagenFile.isEmpty()) {
                System.out.println("Guardando imagen...");
                String fileName = guardarImagen(imagenFile);
                arbol.setRutaImagen("/images/" + fileName);
                System.out.println("Imagen guardada: " + fileName);
            }

            System.out.println("Guardando árbol en BD...");
            arbolService.saveArbol(arbol);
            System.out.println("Árbol guardado exitosamente");

            model.addAttribute("todoOk", "Árbol guardado exitosamente");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Error al guardar: " + e.getMessage());
            return "arbol/modifica";
        }

        return "redirect:/arboles";
    }

    // Mostrar formulario para EDITAR árbol
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Arbol arbol = arbolService.getArbolById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de árbol inválido: " + id));
        model.addAttribute("arbol", arbol);
        return "arbol/modifica";
    }

    // ELIMINAR árbol
    @GetMapping("/eliminar/{id}")
    public String eliminarArbol(@PathVariable Long id) {
        arbolService.deleteArbol(id);
        return "redirect:/arboles";
    }

    // MÉTODO PARA GUARDAR LA IMAGEN
    private String guardarImagen(MultipartFile imagenFile) throws IOException {
        // Ruta ABSOLUTA directa
        String absolutePath = "C:/Users/darry/OneDrive/Documents/NetBeansProjects/practicas/src/main/resources/static/images/";
        File directorio = new File(absolutePath);

        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        String originalFileName = imagenFile.getOriginalFilename();
        String fileExtension = originalFileName != null && originalFileName.contains(".")
                ? originalFileName.substring(originalFileName.lastIndexOf("."))
                : "";

        String fileName = "arbol_" + System.currentTimeMillis() + fileExtension;
        File archivoDestino = new File(absolutePath + fileName);

        imagenFile.transferTo(archivoDestino);
        return fileName;
    }
}
