package com.alura.foro.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
public record DatosRegistroUsuario(
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z ]{3,}$")
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password) {
}
