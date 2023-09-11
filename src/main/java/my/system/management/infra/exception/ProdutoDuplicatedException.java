package my.system.management.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProdutoDuplicatedException extends RuntimeException{

    private final String codigo;

    public ProdutoDuplicatedException(String codigo) {
        super();
        this.codigo = codigo;
    }

    public String getMessage(String codigo) {
        return String.format("Pedido com código %s já cadastrado no sistema", codigo);
    }
}
