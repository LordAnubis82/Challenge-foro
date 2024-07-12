package com.hernanBoggini.Foro_Alura.topico;

import com.hernanBoggini.Foro_Alura.respuestas.RespuestasEntity;
import com.hernanBoggini.Foro_Alura.usuario.UsuarioEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String fecha,
        @NotBlank
        String estatus,
        @NotBlank
        UsuarioEntity usuario,
        List<RespuestasEntity> respuestas
) {
}
