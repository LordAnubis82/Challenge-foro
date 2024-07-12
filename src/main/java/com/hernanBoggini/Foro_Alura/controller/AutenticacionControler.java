package com.hernanBoggini.Foro_Alura.controller;

import com.hernanBoggini.Foro_Alura.security.JWTTokenDTO;
import com.hernanBoggini.Foro_Alura.usuario.UsuarioAutenticar;
import com.hernanBoggini.Foro_Alura.usuario.UsuarioEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.hernanBoggini.Foro_Alura.security.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foro")
@Tag(name = "1. Autenticacion", description = "obtiene el token para el usuario asignado que da acceso al resto de endpoint")

public class AutenticacionControler {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid UsuarioAutenticar usuarioAutenticar){

        Authentication authToken = new UsernamePasswordAuthenticationToken(usuarioAutenticar.login(),
                usuarioAutenticar.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((UsuarioEntity)usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTtoken));
    }

}
