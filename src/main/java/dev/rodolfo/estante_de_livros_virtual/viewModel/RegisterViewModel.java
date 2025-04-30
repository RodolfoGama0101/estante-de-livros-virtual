package dev.rodolfo.estante_de_livros_virtual.viewModel;

import dev.rodolfo.estante_de_livros_virtual.enums.UserRole;

public record RegisterViewModel(String login, String password, UserRole userRole) {

}
