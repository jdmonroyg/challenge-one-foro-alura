package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * @author jdmon on 8/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosRegistroRespuesta (
        @NotBlank
        String mensaje,
        @NotNull
        Long usuario_id,
        @NotNull
        Long topico_id) {
}
