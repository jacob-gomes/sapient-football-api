package com.sapient.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.sapient.constant.Constant;
import com.sapient.controller.model.TeamResponse;
import com.sapient.service.Service;
import com.sapient.service.util.RestCallUtil;

@Component
public class FootBallServiceImpl implements Service {

	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	@Override
	public TeamResponse getRecord(String countryName, String leagueName, String teamName) {
		List<Callable<String>> threadList = new ArrayList<>();
		
		RestCallUtil allCountries = new RestCallUtil(Constant.GET_COUNTRY_URL);
		
		return null;
	}

}
