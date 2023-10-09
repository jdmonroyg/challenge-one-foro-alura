package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.topico.StatusTopico;

import java.time.LocalDateTime;

/**
 * @author jdmon on 8/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosRespuestaRespuesta(
        Long id, String mensaje, LocalDateTime fechacreacion,
        Long usuario_id, Long topico_id) {
    public DatosRespuestaRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(),
                respuesta.getFechacreacion(), respuesta.getUsuario().getId(),
                respuesta.getTopico().getId());
    }
}
