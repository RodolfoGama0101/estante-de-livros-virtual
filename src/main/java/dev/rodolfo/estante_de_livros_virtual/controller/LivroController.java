package dev.rodolfo.estante_de_livros_virtual.controller;

import dev.rodolfo.estante_de_livros_virtual.bean.LivroBean;
import dev.rodolfo.estante_de_livros_virtual.entity.Livro;
import dev.rodolfo.estante_de_livros_virtual.viewModel.LivroViewModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

    @Autowired
    private LivroBean bean;

    @GetMapping("/listar-livros")
    public List<Livro> listarTarefas() {
        return bean.listarTodosOsLivros();
    }

    @GetMapping("/buscar-livros")
    public List<Livro> listarLivrosPorNome(
            @RequestParam String nomeLivro
    ) {
        return bean.buscarLivrosPorNome(nomeLivro);
    }

    @PostMapping("/add-livro")
    public ResponseEntity<Livro> adicionarLivro(
            @RequestBody LivroViewModel viewModel
    ) {
        Livro livroSalvo = bean.addLivro(viewModel);
        return ResponseEntity.status(201).body(livroSalvo);
    }

    @PostMapping("/add-livros")
    public ResponseEntity<List<Livro>> adicionarLivros(
            @Valid
            @RequestBody List<LivroViewModel> viewModel
    ) {
        List<Livro> livrosSalvos = bean.addLivros(viewModel);
        return ResponseEntity.status(201).body(livrosSalvos);
    }

    @PutMapping("/atualizar-info-livro")
    public ResponseEntity<Livro> atualizarLivro(
            @Valid
            @RequestParam Integer idLivro,
            @RequestBody LivroViewModel viewModel
    ) {
        Livro livroAtualizado = bean.atualizarLivro(idLivro, viewModel);
        return ResponseEntity.ok(livroAtualizado);
    }

    @PutMapping("/atualizar-info-livros")
    public ResponseEntity<List<Livro>> atualizarLivros(
            @Valid
            @RequestBody List<LivroViewModel> viewModels
    ) {
        List<Livro> livrosAtualizados = bean.atualizarLivros(viewModels);
        return ResponseEntity.ok(livrosAtualizados);
    }

    @DeleteMapping("/deletar-livro")
    public ResponseEntity<Void> deletarLivro(
            @RequestParam Integer idLivro
    ) {
        bean.deletarLivro(idLivro);
        return ResponseEntity.noContent().build();
    }
}
