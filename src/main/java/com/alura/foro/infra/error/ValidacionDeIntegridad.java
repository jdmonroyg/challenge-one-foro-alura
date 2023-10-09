package com.alura.foro.infra.error;

/**
 * @author jdmon on 5/10/2023.
 * @project challenge-one-foro-alura
 */
public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}

