package com.allura.ForoHub.controller;

import com.allura.ForoHub.infra.Seguridad.TokenService;
import com.allura.ForoHub.users.DatosAutentication;
import com.allura.ForoHub.users.DatosJWToken;
import com.allura.ForoHub.users.UsuariosTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
@PostMapping
    public ResponseEntity authenticateUser(@RequestBody DatosAutentication datosAutentication) {
    Authentication token = new UsernamePasswordAuthenticationToken(datosAutentication.email(), datosAutentication.password());
   var usuarioAutenticado = authenticationManager.authenticate(token);
    var JWToken = tokenService.generarToken((UsuariosTable) usuarioAutenticado.getPrincipal());

    return ResponseEntity.ok(new DatosJWToken(JWToken));
    }
}
