package com.diplomski.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.diplomski.backend.service.CharacterModelService;

@Controller
public class CharacterModelController {
	@Autowired
	private CharacterModelService characterModelService;
}
