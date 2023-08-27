package my.system.management.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TokenNotFoundException extends RuntimeException{

    @Override
    public String getMessage(){
        return "Token não encontrado!";
    }
}
