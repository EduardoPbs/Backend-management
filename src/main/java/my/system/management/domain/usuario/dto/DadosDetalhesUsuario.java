package my.system.management.domain.usuario.dto;

import my.system.management.domain.usuario.model.Usuario;

import java.util.List;

public record DadosDetalhesUsuario(String email, String password, List<String> roles) {
    public DadosDetalhesUsuario(Usuario usuario){
        this(usuario.getLogin(), usuario.getPassword(), usuario.getRoles());
    }
}
