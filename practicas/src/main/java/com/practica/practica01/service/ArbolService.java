/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica.practica01.service;

/**
 *
 * @author darry
 */
import com.practica.practica01.entity.Arbol;
import com.practica.practica01.repository.ArbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArbolService {

    @Autowired
    private ArbolRepository arbolRepository;

    // Obtener todos los árboles
    public List<Arbol> getAllArboles() {
        return arbolRepository.findAll();
    }

    // Obtener árbol por ID
    public Optional<Arbol> getArbolById(Long id) {
        return arbolRepository.findById(id);
    }

    // Guardar o actualizar árbol
    public Arbol saveArbol(Arbol arbol) {
        return arbolRepository.save(arbol);
    }

    // Eliminar árbol
    public void deleteArbol(Long id) {
        arbolRepository.deleteById(id);
    }
}
