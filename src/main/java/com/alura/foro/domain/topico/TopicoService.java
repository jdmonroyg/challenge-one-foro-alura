package com.alura.foro.domain.topico;

import com.alura.foro.infra.error.ValidacionDeIntegridad;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author jdmon on 7/10/2023.
 * @project challenge-one-foro-alura
 */
@Service
@NoArgsConstructor
public class TopicoService {
    @Autowired
    private TopicoRespository topicoRespository;

    public void cerrarTopico(Topico topico) {
        if (topico.getStatus().equals(StatusTopico.SOLUCIONADO)){
            topico.setStatus(StatusTopico.CERRADO);
        }else{
            throw new ValidacionDeIntegridad("El topico debe estar solucionado" +
                    " para poder cerrarlo");
        }
    }
// Validacion que se hace con un entityNotFoundException
//    public Topico existeTopico(Long id){
//        return Optional.of(topicoRespository.getReferenceById(id))
//                .orElseThrow(() -> new ValidacionDeIntegridad("Topico no encontrado"));
//    }

}
