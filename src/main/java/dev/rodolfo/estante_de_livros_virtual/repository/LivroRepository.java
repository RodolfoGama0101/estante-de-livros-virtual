package dev.rodolfo.estante_de_livros_virtual.repository;

import dev.rodolfo.estante_de_livros_virtual.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    List<Livro> findByNomeLivroContainingIgnoreCase(String nomeLivro);
}
