package com.diplomski.backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.diplomski.backend.contract.SimulationRequest;
import com.diplomski.backend.service.SimulationServiceComponent;
import com.diplomski.common.simulation.SimulationReport;

@Controller
public class SimulationController {
	@Autowired
	private SimulationServiceComponent simulationService;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(path = "/simulation/{id}/simulate")
	public ResponseEntity<SimulationReport> getSimulation(
			@PathVariable("id") UUID boardStateId,
			@RequestBody SimulationRequest simulationRequest) {
		SimulationReport response;
		try {
			response = simulationService.getSimulation(boardStateId, simulationRequest
					.getSimulationCount(), simulationRequest.getRoundCountLimit());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
