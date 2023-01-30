package br.com.api.exception.exceptions;

public class PrincipalAddressExistsException extends RuntimeException{
    public PrincipalAddressExistsException(String message) {
        super(message);
    }

}
