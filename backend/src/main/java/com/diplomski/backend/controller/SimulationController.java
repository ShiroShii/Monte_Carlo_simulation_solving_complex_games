package com.diplomski.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.diplomski.backend.contract.SimulationRequest;
import com.diplomski.backend.contract.SimulationResponse;
import com.diplomski.backend.service.SimulationServiceComponent;
import com.diplomski.backend.translator.SimulationTranslator;

@Controller
public class SimulationController {
	@Autowired
	private SimulationServiceComponent simulationService;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(path = "/simulation")
	public ResponseEntity<SimulationResponse> getSimulation(@RequestBody SimulationRequest simulationRequest) {
		SimulationResponse response;
		try {
			response = SimulationTranslator
					.translate(simulationService.getSimulation(simulationRequest.getBattleId(), simulationRequest
							.getSimulationCount(), simulationRequest.getRoundCountLimit()), simulationRequest
									.getBattleId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
