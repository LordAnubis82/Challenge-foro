package com.hernanBoggini.Foro_Alura.controller;


import com.hernanBoggini.Foro_Alura.respuestas.RespuestaRepository;
import com.hernanBoggini.Foro_Alura.respuestas.RespuestasActualizarDTO;
import com.hernanBoggini.Foro_Alura.respuestas.RespuestasEntity;
import com.hernanBoggini.Foro_Alura.servicio.ObtenerUsuario;
import com.hernanBoggini.Foro_Alura.topico.TopicoActualizarDTO;
import com.hernanBoggini.Foro_Alura.topico.TopicoEntity;
import com.hernanBoggini.Foro_Alura.topico.TopicoRepository;
import com.hernanBoggini.Foro_Alura.usuario.UsuarioActualizarDTO;
import com.hernanBoggini.Foro_Alura.usuario.UsuarioEntity;
import com.hernanBoggini.Foro_Alura.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.SecurityMarker;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/foro")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "4. Actualizar", description = "actualizar usuario, topico y/o respuesta")

public class ActualizarController {
    private TopicoRepository topicoRepository;

    private UsuarioRepository usuarioRepository;

    private RespuestaRepository respuestaRepository;

    private ObtenerUsuario obtenerUsuario;

    @Autowired
    public ActualizarController(
            RespuestaRepository respuestaRepository,
            UsuarioRepository usuarioRepository,
            TopicoRepository topicoRepository,
            ObtenerUsuario obtenerUsuario) {

        this.respuestaRepository = respuestaRepository;
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.obtenerUsuario = obtenerUsuario;
    }


    @PutMapping("/usuario-actualizar")
    @Transactional
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioActualizarDTO actualizarDTO){
        UsuarioEntity usuario = this.obtenerUsuario.obtenerSub();
        usuario.actualizar(actualizarDTO);

        var hashpass = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());

        usuario.setClave(hashpass);

        this.usuarioRepository.save(usuario);

        return new ResponseEntity<>("El usuario se actualizo correctamente", HttpStatus.OK);
    }

    @PutMapping("/topico-{id}-actualizar")
    @org.springframework.transaction.annotation.Transactional
    public ResponseEntity<String> actualizarTopico(@PathVariable Long id, @RequestBody TopicoActualizarDTO topicoActualizarDTO) {

        TopicoEntity topico = this.obtenerUsuario.obtenerTopico(id);

        if (topico == null) {
            return new ResponseEntity<>("El tópico no se encontró", HttpStatus.NOT_FOUND);
        }

        topico.actualizar(topicoActualizarDTO);

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        topico.setFecha(localDate.format(formatter));

        this.topicoRepository.save(topico);

        return new ResponseEntity<>("El tópico se actualizó correctamente", HttpStatus.OK);
    }

    @PutMapping("/respuestas-{id}-actualizar")
    @org.springframework.transaction.annotation.Transactional
    public ResponseEntity<String> actualizarRespuesta(@PathVariable Long id,
                                                      @RequestBody RespuestasActualizarDTO respuestasActualizarDTO) {

        RespuestasEntity respuesta = this.obtenerUsuario.obtenerRespuesta(id);

        if (respuesta == null) {
            return new ResponseEntity<>("La respuesta no se encontró", HttpStatus.NOT_FOUND);
        }

        respuesta.actualizar(respuestasActualizarDTO);

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        respuesta.setFecha(localDate.format(formatter));

        this.respuestaRepository.save(respuesta);

        return new ResponseEntity<>("El tópico se actualizó correctamente", HttpStatus.OK);
    }

}