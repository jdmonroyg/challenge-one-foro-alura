package com.alura.foro.domain.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * @author jdmon on 7/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosActualizarTopico(
        String titulo, String mensaje, StatusTopico status){
}