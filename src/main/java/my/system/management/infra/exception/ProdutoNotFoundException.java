package my.system.management.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNotFoundException extends RuntimeException {
    private String id;

    public ProdutoNotFoundException(String id){
        super();
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Produto de ID '%s' não encontrado!", id);
    }
}
