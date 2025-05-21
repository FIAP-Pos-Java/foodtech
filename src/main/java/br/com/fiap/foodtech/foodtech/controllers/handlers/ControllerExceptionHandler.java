package br.com.fiap.foodtech.foodtech.controllers.handlers;

import br.com.fiap.foodtech.foodtech.dtos.ResourceExceptionDTO;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceAlreadyExistsException;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import br.com.fiap.foodtech.foodtech.service.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
