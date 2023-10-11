package com.alura.foro.domain.topico;

import com.alura.foro.domain.respuesta.DatosListadoRespuesta;
import com.alura.foro.domain.respuesta.Respuesta;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jdmon on 7/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosListadoTopico(Long id, String titulo, String mensaje,
                                 LocalDateTime fechacreacion, StatusTopico status,
                                 Long curso_id){
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechacreacion(), topico.getStatus()
                ,topico.getCurso().getId());
    }
}