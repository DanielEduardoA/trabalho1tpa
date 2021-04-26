package br.edu.univas.api.marvel.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.edu.univas.api.marvel.configuration.RestTemplateConfig;
import br.edu.univas.api.marvel.exception.MarvelApiException;

@Service
public class CharactersService {

    @Value("${marvel.all.characters.endpoint}")
    private String marvelAllCharactersEndpoint;

    @Value("${marvel.character.id.endpoint}")
    private String marvelCharacterByIdEndpoint;

    @Value("${marvel.comics.character.id.endpoint}")
    private String marvelComicsByCharacterIdEndpoint;

    @Value("${marvel.events.character.id.endpoint}")
    private String marvelEventsByCharacterIdEndpoint;

    @Value("${marvel.series.character.id.endpoint}")
    private String marvelSeriesByCharacterIdEndpoint;

    @Value("${marvel.stories.character.id.endpoint}")
    private String marvelStoriesCharacterIdEndpoint;

    @Value("${marvel.api.public.key}")
    private String marvelApiPublicKey;

    @Value("${marvel.api.private.key}")
    private String marvelApiPrivateKey;

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    public ResponseEntity<String> listAllCharacters() throws MarvelApiException {
        RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
        return restTemplate.getForEntity(getAllCharactersUrl(), String.class);
    }

    public ResponseEntity<String> listCharacterById(long characterId) throws MarvelApiException {
        RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
        return restTemplate.getForEntity(getCharacterByIdUrl(characterId), String.class);
    }

    public ResponseEntity<String> listAllComicsByCharacterById(long characterId) throws MarvelApiException {
        RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
        return restTemplate.getForEntity(getComicsByCharacterIdUrl(characterId), String.class);
    }

    public ResponseEntity<String> listAllEventsByCharacterById(long characterId) throws MarvelApiException {
        RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
        return restTemplate.getForEntity(getEventsByCharacterIdUrl(characterId), String.class);
    }

    public ResponseEntity<String> listAllSeriesByCharacterById(long characterId) throws MarvelApiException {
        RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
        return restTemplate.getForEntity(getSeriesByCharacterIdUrl(characterId), String.class);
    }

    public ResponseEntity<String> listAllStoriesByCharacterById(long characterId) throws MarvelApiException {
        RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
        return restTemplate.getForEntity(getStoriesByCharacterIdUrl(characterId), String.class);
    }

    public void validateCharacterId(long characterId) throws MarvelApiException {
        if (characterId <= 0) {
            throw new MarvelApiException("O parametro characterId é invalido", HttpStatus.BAD_REQUEST);
        }
    }

    private String getAllCharactersUrl() throws MarvelApiException {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelAllCharactersEndpoint,
                        timestamp,
                        marvelApiPublicKey,
                        generateHash(timestamp)))
                .toString();
    }

    private String getCharacterByIdUrl(long id) throws MarvelApiException  {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelCharacterByIdEndpoint,
                        id,
                        timestamp,
                        marvelApiPublicKey,
                        generateHash(timestamp)))
                .toString();
    }

    private String getComicsByCharacterIdUrl(long id) throws MarvelApiException  {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelComicsByCharacterIdEndpoint,
                        id,
                        timestamp,
                        marvelApiPublicKey,
                        generateHash(timestamp)))
                .toString();
    }

    private String getEventsByCharacterIdUrl(long id) throws MarvelApiException  {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelEventsByCharacterIdEndpoint,
                        id,
                        timestamp,
                        marvelApiPublicKey,
                        generateHash(timestamp)))
                .toString();
    }

    private String getSeriesByCharacterIdUrl(long id) throws MarvelApiException  {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelSeriesByCharacterIdEndpoint,
                        id,
                        timestamp,
                        marvelApiPublicKey,
                        generateHash(timestamp)))
                .toString();
    }

    private String getStoriesByCharacterIdUrl(long id) throws MarvelApiException  {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelStoriesCharacterIdEndpoint,
                        id,
                        timestamp,
                        marvelApiPublicKey,
                        generateHash(timestamp)))
                .toString();
    }

    private String generateHash(long timestamp) throws MarvelApiException  {
        String key = new StringBuilder().append(timestamp).append(marvelApiPrivateKey).append(marvelApiPublicKey).toString();
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(key.getBytes(), 0, key.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new MarvelApiException("Não foi possivel gerar o hash da requisição", HttpStatus.PRECONDITION_FAILED);
        }
        
    }

}