package br.edu.univas.api.marvel.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

    public RestTemplate createRestTemplate() {
        RestTemplateErrorHandler errorHandler = new RestTemplateErrorHandler();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(errorHandler);

        return restTemplate;
    }

}
