package com.alura.foro.domain.topico;

import com.alura.foro.domain.respuesta.DatosListadoRespuesta;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jdmon on 7/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosListadoTopicoConRespuestas(Long id, String titulo, String mensaje,
                                              LocalDateTime fechacreacion, StatusTopico status,
                                              Long curso_id, List<DatosListadoRespuesta> respuestas){
    public DatosListadoTopicoConRespuestas(Topico topico, List<DatosListadoRespuesta> respuestas) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechacreacion(), topico.getStatus()
                ,topico.getCurso().getId(), respuestas);
    }
}