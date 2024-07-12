package com.hernanBoggini.Foro_Alura.respuestas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<RespuestasEntity, Long> {
    void deleteByTopicoId(Long topicoId);
}
