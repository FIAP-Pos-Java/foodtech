package br.com.fiap.foodtech.foodtech.infrastructure.controllers.handlers;

import br.com.fiap.foodtech.foodtech.infrastructure.data.dtos.InternalServerErrorDTO;
import br.com.fiap.foodtech.foodtech.infrastructure.data.dtos.ResourceExceptionDTO;
import br.com.fiap.foodtech.foodtech.infrastructure.data.dtos.ValidationErrorDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.ResourceAlreadyExistsException;
import br.com.fiap.foodtech.foodtech.core.exceptions.ResourceNotFoundException;
import br.com.fiap.foodtech.foodtech.core.exceptions.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceExceptionDTO> handlerResourceNotFoundException(ResourceNotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceExceptionDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ResourceExceptionDTO> handlerResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        var status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status.value()).body(new ResourceExceptionDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResourceExceptionDTO> handlerUnauthorizedException(UnauthorizedException e) {
        var status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status.value()).body(new ResourceExceptionDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> HandleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<String>();
        for (var error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResourceExceptionDTO> handlerDataIntegrityViolationException(DataIntegrityViolationException e) {
        var status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status.value()).body(new ResourceExceptionDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResourceExceptionDTO> handlerIllegalArgumentException(IllegalArgumentException e) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value()).body(new ResourceExceptionDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<InternalServerErrorDTO> handlerInternalServerError(Exception e) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var msg = "Ocorreu um erro interno no servidor.";
        return ResponseEntity.status(status.value()).body(new InternalServerErrorDTO(msg, status.value()));
    }

}
