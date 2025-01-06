package com.allura.ForoHub.foro;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity(name = "DatosTopicoTable")
@Table(name = "Topicos")
@EqualsAndHashCode(of = "id")
public class DatosTopicoTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String nombre_curso;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    public DatosTopicoTable(RegistrarDatosTopico registrarDatosTopico){
        this.titulo = registrarDatosTopico.titulo();
        this.nombre_curso = registrarDatosTopico.nombre_curso();
        this.mensaje = registrarDatosTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
    }
    public void actualizarDatos(@Valid ActualizarDatosTopico datosActualizarTopico) {
        this.id = datosActualizarTopico.id();
        this.titulo = datosActualizarTopico.titulo();
        this.nombre_curso = datosActualizarTopico.nombre_curso();
        this.mensaje = datosActualizarTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
    }

    public DatosTopicoTable(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
    }

    public DatosTopicoTable() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }


}
