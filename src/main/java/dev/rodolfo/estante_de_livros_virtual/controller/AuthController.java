package dev.rodolfo.estante_de_livros_virtual.controller;

import dev.rodolfo.estante_de_livros_virtual.entity.Usuario;
import dev.rodolfo.estante_de_livros_virtual.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody Usuario user ) {
        if (userRepository.findByNome(user.getNome()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        user.setSenha(passwordEncoder.encode(user.getSenha()));
        user.setRole("ADMIN");
        userRepository.save(user);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}
