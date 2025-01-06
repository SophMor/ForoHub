package com.allura.ForoHub.foro;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegistrarDatosTopico(
        @NotNull
        String mensaje,
        @NotNull
        String nombre_curso,
        @NotNull
        String titulo
) {

}
