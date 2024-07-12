package com.hernanBoggini.Foro_Alura.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRegistroDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String clave,
        @NotBlank
        String curso
) {
}
