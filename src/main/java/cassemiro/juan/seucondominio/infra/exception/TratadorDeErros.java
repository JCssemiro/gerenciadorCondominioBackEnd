package cassemiro.juan.seucondominio.infra.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratarErroNaoEncontrado(Exception ex){
        CustomException e = new CustomException(ex.getMessage(),"404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    }
}
