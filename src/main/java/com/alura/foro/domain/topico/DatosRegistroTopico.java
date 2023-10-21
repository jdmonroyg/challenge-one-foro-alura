package com.alura.foro.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosRegistroTopico (
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long usuario_id,
        @NotNull
        Long curso_id){
}
