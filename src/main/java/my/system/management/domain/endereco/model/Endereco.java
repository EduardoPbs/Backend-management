package my.system.management.domain.endereco.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.system.management.domain.endereco.dto.DadosEndereco;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;

    public Endereco(DadosEndereco endereco){
        this.rua = endereco.rua();
        this.bairro = endereco.bairro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
    }

    public void atualizarEndereco(DadosEndereco dados) {
        if(dados.rua() != null){
            this.rua = dados.rua();
        }

        if(dados.bairro() != null){
            this.bairro = dados.bairro();
        }

        if(dados.numero() != null){
            this.numero = dados.numero();
        }

        if(dados.complemento() != null){
            this.complemento = dados.complemento();
        }
    }
}
