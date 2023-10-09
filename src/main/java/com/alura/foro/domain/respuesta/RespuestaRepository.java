package com.alura.foro.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
public interface RespuestaRepository extends JpaRepository<Respuesta,Long> {
    Page<Respuesta> findByActivoTrue(Pageable paginacion);
}
