package com.hernanBoggini.Foro_Alura.errores;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejoDeErrores {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity manejoError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(ErrorValidacionDTO::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record ErrorValidacionDTO(String campo, String error) {
        public ErrorValidacionDTO(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
