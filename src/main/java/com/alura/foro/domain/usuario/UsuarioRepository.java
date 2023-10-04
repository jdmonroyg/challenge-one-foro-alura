package com.alura.foro.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
