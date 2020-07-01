package com.sapient.service;

import com.sapient.controller.model.TeamResponse;

public interface Service {

	TeamResponse getRecord(String countryName, String leagueName, String teamName);

}
