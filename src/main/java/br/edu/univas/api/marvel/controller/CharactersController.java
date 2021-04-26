package br.edu.univas.api.marvel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.api.marvel.exception.MarvelApiException;
import br.edu.univas.api.marvel.service.CharactersService;

@RestController
@RequestMapping("/v1/public/characters")
public class CharactersController {

    @Autowired
    private CharactersService service;

    @GetMapping
    public ResponseEntity<String> listAllCharacters() throws MarvelApiException {
        return service.listAllCharacters();
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<String> listCharacterById(@PathVariable long characterId) throws MarvelApiException {
        service.validateCharacterId(characterId);
        return service.listCharacterById(characterId);
    }

    @GetMapping("/{characterId}/comics")
    public ResponseEntity<String> listAllComicsByCharacterById(@PathVariable long characterId) throws MarvelApiException {
        service.validateCharacterId(characterId);
        return service.listAllComicsByCharacterById(characterId);
    }

    @GetMapping("/{characterId}/events")
    public ResponseEntity<String> listAllEventsByCharacterById(@PathVariable long characterId) throws MarvelApiException {
        service.validateCharacterId(characterId);
        return service.listAllEventsByCharacterById(characterId);
    }

    @GetMapping("/{characterId}/series")
    public ResponseEntity<String> listAllSeriesByCharacterById(@PathVariable long characterId) throws MarvelApiException {
        service.validateCharacterId(characterId);
        return service.listAllSeriesByCharacterById(characterId);
    }

    @GetMapping("/{characterId}/stories")
    public ResponseEntity<String> listAllStoriesByCharacterById(@PathVariable long characterId) throws MarvelApiException {
        service.validateCharacterId(characterId);
        return service.listAllStoriesByCharacterById(characterId);
    }
}