package dev.rodolfo.estante_de_livros_virtual.viewModel;

import dev.rodolfo.estante_de_livros_virtual.enums.StatusLeitura;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LivroViewModel {

    @NotNull
    @Positive
    private Integer idLivro;

    @NotNull
    @Size(max = 100)
    private String nomeLivro;

    @NotNull
    @Size(max = 100)
    private String generoLivro;

    @NotNull
    @Size(max = 100)
    private String autorLivro;

    @NotNull
    @Size(max = 100)
    private Integer numeroPaginas;

    @NotNull
    private StatusLeitura statusLeitura;
}
