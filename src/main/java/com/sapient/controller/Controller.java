package com.sapient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.controller.model.TeamResponse;
import com.sapient.exception.BadRequestException;
import com.sapient.service.Service;
import com.sapient.validate.Validate;

@Component
@RestController
public class Controller {

	private Service footBallService;

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	
	@Autowired
	public Controller(Service footBallService) {
		super();
		this.footBallService = footBallService;
	}
	
	@GetMapping("/get-record")
	public ResponseEntity<TeamResponse> getRecord(
			@RequestParam(value = "countryName", required =false) String countryName,
			@RequestParam(value = "leagueName", required =false) String leagueName,
			@RequestParam(value = "teamName", required =false) String teamName) throws BadRequestException{
		Validate.requestForGetRecord(countryName,leagueName,teamName);
		TeamResponse teamResponse = footBallService.getRecord(countryName,leagueName,teamName);
		
		logger.info("Response: {}",teamResponse != null ? teamResponse.toString(): null);
		return new ResponseEntity<TeamResponse>(teamResponse, HttpStatus.OK);
	}
}
