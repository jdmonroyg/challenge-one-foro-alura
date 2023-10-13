package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.topico.StatusTopico;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.topico.TopicoRespository;
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
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;

    public void validarEstadoTopico(Topico topico) {
        if (!topico.getActivo()){
            throw new ValidacionDeIntegridad("El topico esta inactivo");
        }
        if (topico.getStatus().equals(StatusTopico.CERRADO)){
            throw new ValidacionDeIntegridad("El topico ya esta cerrado");
        }
    }
// lo comento porque ya estoy capturando el error desde entitynotfoundexception
//    public void existeTopico(Long id){
//        if (!respuestaRepository.existsById(id)){
//                throw new ValidacionDeIntegridad("Respuesta no encontrada");
//        }
//    }

}
