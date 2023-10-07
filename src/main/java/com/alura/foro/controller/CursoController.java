package com.alura.foro.controller;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.curso.DatosRegistroCurso;
import com.alura.foro.domain.curso.DatosRespuestaCurso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author jdmon on 5/10/2023.
 * @project challenge-one-foro-alura
 */
@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid
                                        DatosRegistroCurso datosRegistroCurso,
                                        UriComponentsBuilder uriComponentsBuilder){
        Curso curso= cursoRepository.save(new Curso(datosRegistroCurso));
        DatosRespuestaCurso datosRespuestaUsuario= new DatosRespuestaCurso(curso);
        URI url = uriComponentsBuilder.path("/cursos/{id}")
                .buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> retonarDatosCurso(@PathVariable Long id){
        var curso= cursoRepository.getReferenceById(id);
        var datosCurso= new DatosRespuestaCurso(curso);
        return ResponseEntity.ok(datosCurso);

    }
}
