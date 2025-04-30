package dev.rodolfo.estante_de_livros_virtual.entity;

import dev.rodolfo.estante_de_livros_virtual.enums.StatusLeitura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivro;

    private String nomeLivro;

    private String generoLivro;

    private String autorLivro;

    private Integer numeroPaginas;

    @Enumerated(EnumType.STRING)
    private StatusLeitura statusLeitura;
}
