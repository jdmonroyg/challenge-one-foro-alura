package com.alura.foro.domain.usuario;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosRespuestaUsuario(Long id, String nombre, String email) {

    public DatosRespuestaUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());

    }
}
