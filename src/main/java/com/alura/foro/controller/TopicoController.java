package com.alura.foro.controller;

import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.topico.DatosRegistroTopico;
import com.alura.foro.domain.topico.DatosRespuestaTopico;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.topico.TopicoRespository;
import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                              UriComponentsBuilder uriComponentsBuilder ){
        Topico topico =topicoRespository.save(new Topico(datosRegistroTopico,
                usuarioRepository,cursoRepository));
        DatosRespuestaTopico datosRespuestaTopico=new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("{/id}").buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosTopico(@PathVariable Long id){
        Topico topico= topicoRespository.getReferenceById(id);
        DatosRespuestaTopico datosRespuestaTopico= new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datosRespuestaTopico);
    }

}
