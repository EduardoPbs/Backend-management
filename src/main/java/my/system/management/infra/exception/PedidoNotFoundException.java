package my.system.management.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException{

    @Override
    public String getMessage(){
        return "Pedido não encontrado!";
    }
}
