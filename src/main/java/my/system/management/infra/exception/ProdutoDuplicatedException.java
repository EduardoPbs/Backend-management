package my.system.management.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProdutoDuplicatedException extends RuntimeException{

    private final String code;

    public ProdutoDuplicatedException(String code) {
        super();
        this.code = code;
    }

    @Override
    public String getMessage() {
        return String.format("Produto com código %s já cadastrado no sistema.", code);
    }
}
