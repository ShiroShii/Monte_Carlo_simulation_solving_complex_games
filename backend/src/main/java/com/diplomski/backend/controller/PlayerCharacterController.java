package com.diplomski.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.diplomski.backend.contract.PlayerCharacterCreateRequest;
import com.diplomski.backend.contract.PlayerCharacterResponse;
import com.diplomski.backend.dal.PlayerCharacterDbModel;
import com.diplomski.backend.service.PlayerCharacterService;
import com.diplomski.backend.translator.PlayerCharacterTranslator;

@Controller
public class PlayerCharacterController {
	@Autowired
	private PlayerCharacterService playerCharacterService;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(path = "/player-character")
	public ResponseEntity<PlayerCharacterResponse> create(
			@RequestBody PlayerCharacterCreateRequest request) {
		PlayerCharacterDbModel dbResponse = playerCharacterService
				.save(request);
		PlayerCharacterResponse response = PlayerCharacterTranslator
				.translate(dbResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(path = "/player-character/{id}")
	public ResponseEntity<PlayerCharacterResponse> update(
			@PathVariable("id") UUID id,
			@RequestBody PlayerCharacterCreateRequest request) {
		PlayerCharacterDbModel dbResponse = playerCharacterService
				.save(id, request);
		PlayerCharacterResponse response = PlayerCharacterTranslator
				.translate(dbResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(path = "/player-character/{id}")
	public ResponseEntity<PlayerCharacterResponse> get(
			@PathVariable("id") UUID id) {
		PlayerCharacterDbModel dbResponse = playerCharacterService
				.get(id).get();
		PlayerCharacterResponse response = PlayerCharacterTranslator
				.translate(dbResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(path = "/player-character")
	public ResponseEntity<List<PlayerCharacterResponse>> getAll() {
		List<PlayerCharacterDbModel> dbResponse = playerCharacterService
				.getAll();
		List<PlayerCharacterResponse> response = PlayerCharacterTranslator
				.translate(dbResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
