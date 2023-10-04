package com.alura.foro.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
public interface TopicoRespository extends JpaRepository<Topico,Long> {
}
