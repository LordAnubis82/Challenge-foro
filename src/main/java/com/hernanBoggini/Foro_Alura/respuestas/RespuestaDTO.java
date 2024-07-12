package com.hernanBoggini.Foro_Alura.respuestas;

import com.hernanBoggini.Foro_Alura.topico.TopicoEntity;
import com.hernanBoggini.Foro_Alura.usuario.UsuarioEntity;
import jakarta.validation.constraints.NotBlank;

public record RespuestaDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String respuesta,
        @NotBlank
        String fecha,
        @NotBlank
        UsuarioEntity usuario,
        @NotBlank
        TopicoEntity topico
) {
}
