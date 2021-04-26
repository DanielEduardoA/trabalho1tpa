package br.edu.univas.api.marvel.configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import br.edu.univas.api.marvel.exception.MarvelApiException;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError()) {
            byte[] responseBody = response.getBody().readAllBytes();
            String body = new String(responseBody, StandardCharsets.UTF_8);
            throw new MarvelApiException(body, response.getStatusCode());
        } 
    }

}
