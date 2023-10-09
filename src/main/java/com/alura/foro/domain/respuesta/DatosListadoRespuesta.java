package com.alura.foro.domain.respuesta;

import java.time.LocalDateTime;

/**
 * @author jdmon on 8/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosListadoRespuesta(
        Long id, String mensaje, LocalDateTime fechacreacion, Boolean solucion,
        Long usuario_id, Long topico_id) {
    public DatosListadoRespuesta (Respuesta respuesta){
        this(respuesta.getId(),respuesta.getMensaje(),respuesta.getFechacreacion(),
                respuesta.getSolucion(),respuesta.getUsuario().getId(),
                respuesta.getTopico().getId());
    }
}
