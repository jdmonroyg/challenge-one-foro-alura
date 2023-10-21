package com.alura.foro.domain.curso;

import jakarta.validation.constraints.NotNull;

/**
 * @author jdmon on 5/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosRegistroCurso(
        @NotNull
        Nombre nombre,
        @NotNull
        Categoria categoria ) {
}
