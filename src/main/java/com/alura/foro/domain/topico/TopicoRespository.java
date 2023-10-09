package com.alura.foro.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
public interface TopicoRespository extends JpaRepository<Topico,Long> {
    Page<Topico> findByActivoTrue(Pageable paginacion);
}
