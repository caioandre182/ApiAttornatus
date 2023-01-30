package br.com.api.exception;

import br.com.api.exception.exceptions.AddressNotFoundException;
import br.com.api.exception.exceptions.PersonNotFoundException;
import br.com.api.exception.exceptions.PrincipalAddressExistsException;
import br.com.api.exception.response.ExceptionResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse badRequestResponse(Exception e) {
        return new ExceptionResponse(e.getMessage(), 400);
    }

    @ExceptionHandler(PrincipalAddressExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse principalAddressExistsResponse(PrincipalAddressExistsException e) {
        return new ExceptionResponse(e.getMessage(), 409);
    }

    @ExceptionHandler({PersonNotFoundException.class, AddressNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundResponse(Exception e) {
        return new ExceptionResponse(e.getMessage(), 404);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse methodArgumentNotValidResponse(Exception e) {
        return new ExceptionResponse(e.getMessage(), 400);
    }
}
