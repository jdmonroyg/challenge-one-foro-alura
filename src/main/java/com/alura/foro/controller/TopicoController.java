package com.alura.foro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @GetMapping
    public String helloWorld(){
        return "Hola controller topico";
    }
}
