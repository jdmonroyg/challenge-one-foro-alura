package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosRuespuestaTopico(String titulo, String mensaje, LocalDateTime fechaCreacion,
                                    StatusTopico status, Usuario usuario, Curso curso){
}
