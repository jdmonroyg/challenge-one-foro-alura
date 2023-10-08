package com.alura.foro.error;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author jdmon on 4/10/2023.
 * @project challenge-one-foro-alura
 */
@RestControllerAdvice // esto para capturar
public class TratadorError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFoundException() {
        return ResponseEntity.notFound().build();
    }
//    hacerlo en validaciones

    @ExceptionHandler (SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(
            SQLIntegrityConstraintViolationException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                "Error de integridad de datos: \n"+exception.getMessage()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErrorValidation>> handleMethodArgumentValidationErrors(
            MethodArgumentNotValidException exception){
        var errores=exception.getFieldErrors().stream().map(DatosErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errores);

    }
    private record DatosErrorValidation(String campo, String error){
        public DatosErrorValidation(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }

    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity<String> handleValidacionDeIntegridad(Exception exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
