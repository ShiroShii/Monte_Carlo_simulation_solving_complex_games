package com.diplomski.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.diplomski.backend.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
}
