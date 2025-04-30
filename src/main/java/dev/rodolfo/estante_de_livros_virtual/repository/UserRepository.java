package dev.rodolfo.estante_de_livros_virtual.repository;

import dev.rodolfo.estante_de_livros_virtual.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
    UserDetails findByLogin(String login);
}