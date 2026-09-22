package com.mdw.security.controller;

import com.mdw.security.repository.LibroRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping("/libros")
    public String listar(Model model) {
        model.addAttribute("libros", libroRepository.findAll());
        return "libros";
    }

    @GetMapping("/buscar")
    public String buscar(
            @RequestParam(required = false, defaultValue = "") String q,
            Model model) {

        if (q.isBlank()) {
            model.addAttribute("libros", libroRepository.findAll());
        } else {
            model.addAttribute(
                    "libros",
                    libroRepository
                            .findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(q, q)
            );
        }

        model.addAttribute("q", q);

        return "buscar";
    }
}
