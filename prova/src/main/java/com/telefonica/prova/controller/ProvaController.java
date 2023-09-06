package com.telefonica.prova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.telefonica.prova.service.running.RunningService;
import com.telefonica.prova.service.vector.VectorService;

@RestController
@RequestMapping
public class ProvaController {
	
	@Autowired VectorService provaService;
	@Autowired RunningService runningService;
	
	@GetMapping("/vectorCount")
	@ResponseStatus(code = HttpStatus.OK)
	public String getVectorCount() {		
		int[] vetor = {0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15};		
		return provaService.vectorCount(vetor);
	}
	
	@GetMapping("/preLoaded")
	@ResponseStatus(code = HttpStatus.OK)
	public String getVectorCountPreLoaded() {
		int[] vetor = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 18, 12, 13, 14, 15};		
		return provaService.vectorCountPreLoaded(vetor);
	}
	
	@GetMapping(path="/vectorCountJSON", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String getVectorCountJSON() {		
		int[] vetor = {0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 15};
		return new Gson().toJson(provaService.vectorCountJSON(vetor));
	}
	
	
	@GetMapping("/running")
	@ResponseStatus(code = HttpStatus.OK)
	public String getRunning() {		
		return runningService.finishLine();
	}
}
