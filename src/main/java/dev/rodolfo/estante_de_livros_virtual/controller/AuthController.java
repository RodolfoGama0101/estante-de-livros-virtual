package dev.rodolfo.estante_de_livros_virtual.controller;

import dev.rodolfo.estante_de_livros_virtual.bean.TokenBean;
import dev.rodolfo.estante_de_livros_virtual.entity.Users;
import dev.rodolfo.estante_de_livros_virtual.repository.UserRepository;
import dev.rodolfo.estante_de_livros_virtual.response.LoginResponse;
import dev.rodolfo.estante_de_livros_virtual.viewModel.LoginViewModel;
import dev.rodolfo.estante_de_livros_virtual.viewModel.RegisterViewModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenBean tokenBean;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody @Valid LoginViewModel viewModel
    ) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(viewModel.login(), viewModel.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenBean.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterViewModel viewModel) {
        if (viewModel.password() == null || viewModel.password().isBlank()) {
            return ResponseEntity.badRequest().body("Password cannot be null or blank");
        }

        if (userRepository.findByLogin(viewModel.login()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(viewModel.password());
        Users newUser = new Users(viewModel.login(), encryptedPassword, viewModel.userRole());

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
