package com.mdw.security.controller;

import com.mdw.security.entity.Usuario;
import com.mdw.security.repository.UsuarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PerfilController {

    private final UsuarioRepository usuarioRepository;

    public PerfilController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/perfil")
    public String perfil(Principal principal, Model model) {

        Usuario usuario = usuarioRepository
                .findByUsername(principal.getName())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        model.addAttribute("usuario", usuario);

        return "perfil";
    }
}
