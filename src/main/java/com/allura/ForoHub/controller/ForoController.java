package com.allura.ForoHub.controller;


import com.allura.ForoHub.foro.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class ForoController {
     @Autowired
    private Repositorio topicosRepository;


    @PostMapping
    public ResponseEntity crearTopico(@RequestBody @Valid RegistrarDatosTopico datosTopico,
                                      UriComponentsBuilder uriBuilder) {
        Optional<DatosTopicoTable> existingTopico = topicosRepository.findByTituloAndMensaje(
                datosTopico.titulo(), datosTopico.mensaje());
        if (existingTopico.isPresent()) {
            throw new ValidacionException("Ya existe un mensaje con ese titulo");
        }
        if (datosTopico.nombre_curso() == null||datosTopico.nombre_curso().isEmpty()||datosTopico.mensaje().isEmpty()
        ||datosTopico.titulo().isEmpty()) {
            throw new ValidacionException("no puede ser nulo.");
        }
        DatosTopicoTable datosTopicoTable = topicosRepository.save(new DatosTopicoTable(datosTopico));

        DatosTopico datosTopicoRespuesta = new DatosTopico(datosTopicoTable);

        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(datosTopicoTable.getId()).toUri();

        return ResponseEntity.created(url).body(datosTopicoRespuesta);
    }


    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<DatosTopico>> obtenerTopicos(@PageableDefault (size = 3) Pageable paginacion){
        var datosRespuesta = topicosRepository.findAll(paginacion).map(DatosTopico::new);

        return ResponseEntity.ok((org.springframework.data.domain.Page<DatosTopico>) datosRespuesta);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosTopico> retornaDatosTopicos(@PathVariable Long id) {
        DatosTopicoTable datosTopicoTable = topicosRepository.getReferenceById(id);
        var datosMedico = new DatosTopico(datosTopicoTable.getId(),
                datosTopicoTable.getTitulo(),
                datosTopicoTable.getMensaje(),
                datosTopicoTable.getNombre_curso(),
                datosTopicoTable.getFechaCreacion());
        return ResponseEntity.ok(datosMedico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Optional<DatosTopicoTable> topico = topicosRepository.findById(id);
        if (topico.isPresent()) {
            topicosRepository.delete(topico.get());
        }
        else{
            throw new ValidacionException("No existe un mensaje con ese id");
        }
        return ResponseEntity.ok().build();
    }
    @PutMapping
    @Transactional
    public ResponseEntity actualizarDatos(@RequestBody @Valid ActualizarDatosTopico datosActualizarTopico) {
        if (datosActualizarTopico.id() == null) {
            throw new ValidacionException("El ID no puede ser nulo.");
        }
        DatosTopicoTable datosTopicoTable = topicosRepository.getReferenceById(datosActualizarTopico.id());
        datosTopicoTable.actualizarDatos(datosActualizarTopico);

        Optional<DatosTopicoTable> existingTopico = topicosRepository.findByTituloAndMensaje(
                datosActualizarTopico.titulo(), datosActualizarTopico.mensaje());
        if (existingTopico.isPresent() && !existingTopico.get().getId().equals(datosTopicoTable.getId())) {
            throw new ValidacionException("Ya existe un mensaje con ese título y mensaje.");
        }
        DatosTopico datosTopico = new DatosTopico(
                datosTopicoTable.getId(),
                datosTopicoTable.getNombre_curso(),
                datosTopicoTable.getMensaje(),
                datosTopicoTable.getTitulo(),
                datosTopicoTable.getFechaCreacion()
        );
        return ResponseEntity.ok(datosTopico);
    }



}
