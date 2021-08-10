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

import com.diplomski.backend.contract.NodeBoardCreateRequest;
import com.diplomski.backend.contract.NodeBoardResponse;
import com.diplomski.backend.service.BoardService;
import com.diplomski.backend.translator.NodeBoardTranslator;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(path = "/board")
	public ResponseEntity<NodeBoardResponse> create(
			@RequestBody NodeBoardCreateRequest request) {
		NodeBoardResponse response = NodeBoardTranslator.translate(boardService.saveBoard(request));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(path = "/board/{id}")
	public ResponseEntity<NodeBoardResponse> get(
			@PathVariable("id") UUID boardId) {
		NodeBoardResponse response = NodeBoardTranslator.translate(boardService.getBoard(boardId).get());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
