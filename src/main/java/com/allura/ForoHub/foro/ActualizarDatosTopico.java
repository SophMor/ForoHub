package com.allura.ForoHub.foro;

import jakarta.validation.constraints.NotNull;

public record ActualizarDatosTopico(@NotNull Long id,
                                   String titulo,
                                    String nombre_curso,
                                    String mensaje ) {
}
