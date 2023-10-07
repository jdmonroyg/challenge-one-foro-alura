package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosRespuestaTopico(Long id, String titulo, String mensaje,
                                   LocalDateTime fechacreacion, StatusTopico status,
                                   Long usuario_id, Long curso_id){
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechacreacion(), topico.getStatus(),topico.getUsuario().getId(),
                topico.getCurso().getId());
    }
}
