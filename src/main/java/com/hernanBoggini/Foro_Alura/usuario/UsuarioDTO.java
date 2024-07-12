package com.hernanBoggini.Foro_Alura.usuario;

import com.hernanBoggini.Foro_Alura.respuestas.RespuestasEntity;
import com.hernanBoggini.Foro_Alura.topico.TopicoEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public record UsuarioDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String clave,
        @NotBlank
        String curso,
        List<TopicoEntity> topicos,
        List<RespuestasEntity> respuestas) {
}
