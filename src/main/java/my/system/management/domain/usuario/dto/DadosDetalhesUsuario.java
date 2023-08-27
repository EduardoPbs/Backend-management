package my.system.management.domain.usuario.dto;

import my.system.management.domain.usuario.model.Usuario;

public record DadosDetalhesUsuario(String email, String password) {
    public DadosDetalhesUsuario(Usuario usuario){
        this(usuario.getLogin(), usuario.getPassword());
    }
}
