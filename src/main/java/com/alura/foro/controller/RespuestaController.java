package com.alura.foro.controller;

import com.alura.foro.domain.respuesta.*;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.topico.TopicoRespository;
import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author jdmon on 8/10/2023.
 * @project challenge-one-foro-alura
 */
@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRespository topicoRespository;

    @Autowired
    private RespuestaService respuestaService;
    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrarRespuesta(
            @RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
            UriComponentsBuilder uriComponentsBuilder){
        Topico topico =topicoRespository.getReferenceById(datosRegistroRespuesta.topico_id());
        respuestaService.validarEstadoTopico(topico);
        Respuesta respuesta= respuestaRepository.save(
                new Respuesta(datosRegistroRespuesta,usuarioRepository,
                        topico));
        DatosRespuestaRespuesta datosRespuestaRespuesta =
                new DatosRespuestaRespuesta(respuesta);
        URI url = uriComponentsBuilder.path("respuestas/{id}")
                .buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page> listarRespuestas(
            @PageableDefault (size = 5, sort = "fechacreacion",
                    direction = Sort.Direction.DESC) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findByActivoTrue(paginacion).
                map(DatosListadoRespuesta::new));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DatosRespuestaRespuesta>retonarDatosRespuesta(
            @PathVariable Long id){
        Respuesta respuesta = getRespuesta(id);
        var datosRespuesta= new DatosRespuestaRespuesta(respuesta);
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaRespuesta> actualizarRespuesta(
            @PathVariable @NotNull Long id,
            @RequestBody DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta = getRespuesta(id);
        respuesta.actualizarRespuesta(datosActualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id){
        //respuestaService.existeTopico(id);
        Respuesta respuesta= getRespuesta(id);
        respuesta.desactivarRespuesta();
        return ResponseEntity.noContent().build();
    }

    private Respuesta getRespuesta(Long id) {
        return respuestaRepository.getReferenceById(id);
    }


}
