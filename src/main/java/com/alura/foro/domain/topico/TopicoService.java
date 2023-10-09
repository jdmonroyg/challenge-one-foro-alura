package com.alura.foro.domain.topico;

import com.alura.foro.infra.error.ValidacionDeIntegridad;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jdmon on 7/10/2023.
 * @project challenge-one-foro-alura
 */
@Service
@NoArgsConstructor
public class TopicoService {
    @Autowired
    private TopicoRespository topicoRespository;

    public void existeTopico(Long id){
        if (!topicoRespository.existsById(id)){
                throw new ValidacionDeIntegridad("Topico no encontrado");
        }
    }

}
