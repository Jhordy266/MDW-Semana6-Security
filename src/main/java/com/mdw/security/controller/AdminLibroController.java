package com.mdw.security.controller;

import com.mdw.security.entity.Libro;
import com.mdw.security.repository.LibroRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/libros")
public class AdminLibroController {

    private final LibroRepository libroRepository;

    public AdminLibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("libros", libroRepository.findAll());
        return "admin-libros";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("libro", new Libro());

        return "libro-form";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        Libro libro = libroRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Libro no encontrado"));

        model.addAttribute("libro", libro);

        return "libro-form";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Libro libro) {

        libroRepository.save(libro);

        return "redirect:/admin/libros";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(
            @PathVariable Long id) {

        libroRepository.deleteById(id);

        return "redirect:/admin/libros";
    }
}
