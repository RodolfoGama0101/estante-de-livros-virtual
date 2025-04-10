package dev.rodolfo.estante_de_livros_virtual.bean;

import dev.rodolfo.estante_de_livros_virtual.entity.Livro;
import dev.rodolfo.estante_de_livros_virtual.repository.LivroRepository;
import dev.rodolfo.estante_de_livros_virtual.viewModel.LivroViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroBean {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodosOsLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarLivrosPorNome(String nomeLivro) {
        return livroRepository.findByNomeLivroContainingIgnoreCase(nomeLivro);
    }

    public Livro addLivro(LivroViewModel viewModel) {
        Livro livro = new Livro();
        livro.setNomeLivro(viewModel.getNomeLivro());
        livro.setGeneroLivro(viewModel.getGeneroLivro());
        livro.setAutorLivro(viewModel.getAutorLivro());

        return livroRepository.save(livro);
    }

    public List<Livro> addLivros(List<LivroViewModel> viewModels) {
        List<Livro> livros = viewModels.stream().map(viewModel -> {
            Livro livro = new Livro();
            livro.setNomeLivro(viewModel.getNomeLivro());
            livro.setGeneroLivro(viewModel.getGeneroLivro());
            livro.setAutorLivro(viewModel.getAutorLivro());
            return livro;
        }).toList();

        return livroRepository.saveAll(livros);
    }

    public Livro atualizarLivro(Integer idLivro, LivroViewModel viewModel) {
        Optional<Livro> livroOptional = livroRepository.findById(idLivro);

        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();

            if (viewModel.getNomeLivro() != null) {
                livro.setNomeLivro(viewModel.getNomeLivro());
            }

            if (viewModel.getGeneroLivro() != null) {
                livro.setGeneroLivro(viewModel.getGeneroLivro());
            }

            if (viewModel.getAutorLivro() != null) {
                livro.setAutorLivro(viewModel.getAutorLivro());
            }

            return livroRepository.save(livro);
        } else {
            throw new RuntimeException("Livro com ID " + idLivro + " não encontrado.");
        }
    }

    public void deletarLivro(Integer idLivro){
        if (livroRepository.existsById(idLivro)) {
            livroRepository.deleteById(idLivro);
        } else {
            throw new RuntimeException("Livro com ID " + idLivro + " não encontrado.");
        }
    }
}
