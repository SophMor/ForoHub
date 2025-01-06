package com.allura.ForoHub.foro;

import java.time.LocalDateTime;

// Clase encargada de mostrar
public record DatosTopico(
        Long id,
        String mensaje,
        String nombre_curso,
        String titulo,
        LocalDateTime fechaCreacion
) {
    // Constructor no canónico que delega al constructor canónico
    public DatosTopico(DatosTopicoTable datosTopicoTable) {
        this(  // Llamada al constructor canónico
                datosTopicoTable.getId(),
                datosTopicoTable.getMensaje(),
                datosTopicoTable.getNombre_curso(),
                datosTopicoTable.getTitulo(),
                datosTopicoTable.getFechaCreacion()
        );
    }
}
