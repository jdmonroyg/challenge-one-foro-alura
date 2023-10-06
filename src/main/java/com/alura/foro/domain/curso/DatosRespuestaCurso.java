package com.alura.foro.domain.curso;

/**
 * @author jdmon on 5/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosRespuestaCurso(Long id, Nombre nombre, Categoria categoria) {

    public DatosRespuestaCurso(Curso curso) {
        this(curso.getId(),curso.getNombre(),curso.getCategoria());
    }
}
