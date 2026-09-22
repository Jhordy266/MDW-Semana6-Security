package com.mdw.security.config;

import com.mdw.security.entity.Rol;
import com.mdw.security.entity.Usuario;
import com.mdw.security.repository.RolRepository;
import com.mdw.security.repository.UsuarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            RolRepository rolRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        Rol rolUser = rolRepository.findByNombre("ROLE_USER")
                .orElseThrow(() ->
                        new RuntimeException("ROLE_USER no existe"));

        Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN")
                .orElseThrow(() ->
                        new RuntimeException("ROLE_ADMIN no existe"));

        if (usuarioRepository.findByUsername("usuario").isEmpty()) {

            Usuario usuario = new Usuario();

            usuario.setUsername("usuario");
            usuario.setNombre("Usuario Normal");

            usuario.setPassword(
                    passwordEncoder.encode("123456")
            );

            usuario.setRol(rolUser);

            usuarioRepository.save(usuario);
        }

        if (usuarioRepository.findByUsername("admin").isEmpty()) {

            Usuario admin = new Usuario();

            admin.setUsername("admin");
            admin.setNombre("Administrador");

            admin.setPassword(
                    passwordEncoder.encode("admin123")
            );

            admin.setRol(rolAdmin);

            usuarioRepository.save(admin);
        }
    }
}
