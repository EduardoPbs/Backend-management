package my.system.management.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TrataErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erro400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DadosErrors::new));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro400(){
        return ResponseEntity.notFound().build();
    }

    private record DadosErrors(String field, String message){
        public DadosErrors(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
