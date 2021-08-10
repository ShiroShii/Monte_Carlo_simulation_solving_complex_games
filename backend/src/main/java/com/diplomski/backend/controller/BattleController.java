package com.diplomski.backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.diplomski.backend.contract.BattleCreateRequest;
import com.diplomski.backend.contract.BattleResponse;
import com.diplomski.backend.service.BattleService;
import com.diplomski.backend.translator.BattleTranslator;

@Controller
public class BattleController {
	@Autowired
	private BattleService battleService;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(path = "/battle")
	public ResponseEntity<BattleResponse> create(
			@RequestBody BattleCreateRequest request){
		BattleResponse response;
		try {
			response = BattleTranslator.translate(battleService.save(request));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(path = "/battle/{id}")
	public ResponseEntity<BattleResponse> get(
			@PathVariable("id") UUID id) {
		BattleResponse response = BattleTranslator.translate(battleService.get(id).get());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
