package com.practica.practica01.repository;

import com.practica.practica01.entity.Arbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbolRepository extends JpaRepository<Arbol, Long> {
    // Spring Data JPA crea automáticamente:
    // findAll(), findById(), save(), deleteById(), etc.
}