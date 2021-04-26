package br.edu.univas.api.marvel.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;

public class MarvelApiException extends IOException {

    private static final long serialVersionUID = -1829581932881180093L;

    private HttpStatus httpStatus;

    public MarvelApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public MarvelApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
