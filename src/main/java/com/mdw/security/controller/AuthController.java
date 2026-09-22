package com.mdw.security.controller;

import com.mdw.security.entity.Usuario;
import com.mdw.security.repository.UsuarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String raiz() {
        return "redirect:/inicio";
    }

    @GetMapping("/inicio")
    public String inicio(Principal principal, Model model) {

        Usuario usuario = usuarioRepository
                .findByUsername(principal.getName())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        model.addAttribute("usuario", usuario);

        return "inicio";
    }
}