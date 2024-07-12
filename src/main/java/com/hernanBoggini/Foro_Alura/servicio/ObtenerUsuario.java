package com.hernanBoggini.Foro_Alura.servicio;

import com.hernanBoggini.Foro_Alura.respuestas.RespuestasEntity;
import com.hernanBoggini.Foro_Alura.topico.TopicoEntity;
import com.hernanBoggini.Foro_Alura.usuario.UsuarioEntity;
import com.hernanBoggini.Foro_Alura.usuario.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hernanBoggini.Foro_Alura.security.TokenService;

    @Service
public class ObtenerUsuario {


        private HttpServletRequest request;

        private TokenService tokenService;

        private UsuarioRepository usuarioRepository;

        @Autowired
        public ObtenerUsuario(HttpServletRequest request
                , TokenService tokenService
                , UsuarioRepository usuarioRepository) {

            this.request = request;
            this.tokenService = tokenService;
            this.usuarioRepository = usuarioRepository;

        }

        public TopicoEntity obtenerTopico(Long id){

            UsuarioEntity usuarioEntity = obtenerSub();

            TopicoEntity topico = usuarioEntity.getTopicos().stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            return topico;

        }

        public RespuestasEntity obtenerRespuesta(Long id){

            UsuarioEntity usuarioEntity = obtenerSub();

            RespuestasEntity respuesta = usuarioEntity.getRespuestas().stream()
                    .filter(r -> r.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            return respuesta;

        }

        public UsuarioEntity obtenerSub() {

            var authHeader = this.request.getHeader("Authorization");

            var token = authHeader.replace("Bearer ", "");

            var nombreUsuario = this.tokenService.getSubject(token);

            return (UsuarioEntity) usuarioRepository.findByNombre(nombreUsuario);

        }

}
