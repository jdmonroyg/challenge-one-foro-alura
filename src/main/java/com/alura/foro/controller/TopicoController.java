package com.alura.foro.controller;

import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.respuesta.DatosListadoRespuesta;
import com.alura.foro.domain.topico.*;
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
import java.util.List;


/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRespository topicoRespository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                      UriComponentsBuilder uriComponentsBuilder ){
        Topico topico =topicoRespository.save(new Topico(datosRegistroTopico,
                usuarioRepository,cursoRepository));
        DatosRespuestaTopico datosRespuestaTopico=new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("/{id}").buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(
            @PageableDefault(size=5, sort = "fechacreacion",
            direction= Sort.Direction.DESC)  Pageable paginacion){
        return ResponseEntity.ok(topicoRespository.findByActivoTrue(paginacion)
                .map(DatosListadoTopico::new));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DatosListadoTopicoConRespuestas> retornarDatosTopico(
            @PathVariable Long id){
        Topico topico= topicoRespository.getReferenceById(id);
        List<DatosListadoRespuesta> respuestas = topico.getRespuestas()
                .stream().map(DatosListadoRespuesta::new).toList();
        DatosListadoTopicoConRespuestas datosListadoTopicoConRespuestas=
                new DatosListadoTopicoConRespuestas(topico,respuestas);
        return ResponseEntity.ok(datosListadoTopicoConRespuestas);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarDatosTopico(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico=getTopico(id);
        topico.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));

    }
    // implemente un sotf delete
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id){
        topicoService.existeTopico(id);
        Topico topico=getTopico(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    private Topico getTopico(Long id) {
        return topicoRespository.getReferenceById(id);
    }

}
