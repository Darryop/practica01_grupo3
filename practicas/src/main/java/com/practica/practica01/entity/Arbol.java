package com.practica.practica01.entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "arbol")
public class Arbol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_comun")
    private String nombreComun;

    @Column(name = "tipo_flor")
    private String tipoFlor;

    @Column(name = "dureza_madera")
    private Integer durezaMadera;

    @Column(name = "altura_promedio")
    private Double alturaPromedio;

    @Column(name = "ruta_imagen")
    private String rutaImagen;


    // Constructor vacío
    public Arbol() {
    }

    // Constructor con parámetros
    public Arbol(String nombreComun, String tipoFlor, Integer durezaMadera, Double alturaPromedio, String rutaImagen) {
        this.nombreComun = nombreComun;
        this.tipoFlor = tipoFlor;
        this.durezaMadera = durezaMadera;
        this.alturaPromedio = alturaPromedio;
        this.rutaImagen = rutaImagen;
    }

    // GETTERS Y SETTERS (te los paso completos ahora)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getTipoFlor() {
        return tipoFlor;
    }

    public void setTipoFlor(String tipoFlor) {
        this.tipoFlor = tipoFlor;
    }

    public Integer getDurezaMadera() {
        return durezaMadera;
    }

    public void setDurezaMadera(Integer durezaMadera) {
        this.durezaMadera = durezaMadera;
    }

    public Double getAlturaPromedio() {
        return alturaPromedio;
    }

    public void setAlturaPromedio(Double alturaPromedio) {
        this.alturaPromedio = alturaPromedio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
