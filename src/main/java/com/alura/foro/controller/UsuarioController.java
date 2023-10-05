package com.alura.foro.controller;

import com.alura.foro.domain.usuario.DatosRegistroUsuario;
import com.alura.foro.domain.usuario.DatosRespuestaUsuario;
import com.alura.foro.domain.usuario.Usuario;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid
                       DatosRegistroUsuario datosRegistroUsuario,
                                                   UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario= usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosRespuestaUsuario datosRespuestaUsuario= new DatosRespuestaUsuario(usuario);
        URI url = uriComponentsBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> retornarDatosUsuario(
            @PathVariable Long id){ //tag importante para asignar el valor de id
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario= new DatosRespuestaUsuario(usuario);
        return ResponseEntity.ok(datosUsuario);
    }
}
