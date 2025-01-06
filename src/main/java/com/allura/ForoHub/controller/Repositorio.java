package com.allura.ForoHub.controller;

import com.allura.ForoHub.foro.DatosTopicoTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repositorio extends JpaRepository<DatosTopicoTable, Long> {
    Optional<DatosTopicoTable> findByTituloAndMensaje(String titulo, String mensaje);

}
