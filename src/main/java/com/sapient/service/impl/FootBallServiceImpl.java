package com.sapient.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.sapient.constant.Constant;
import com.sapient.controller.model.TeamResponse;
import com.sapient.service.Service;
import com.sapient.service.util.RestCallUtil;

@Component
public class FootBallServiceImpl implements Service {

	//private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Override
	public TeamResponse getRecord(String countryName, String leagueName, String teamName) {


		TeamResponse teamResponse = new TeamResponse();

		teamResponse.setCountryName(countryName); 
		teamResponse.setLeagueName(leagueName);
		teamResponse.setTeamName(teamName);

		teamResponse.setCountryId(
				requestRestCallAndProcessResponse(countryName, teamResponse,Constant.GET_COUNTRY_URL, "country_name", "country_id",null, null)
				);


		if(teamResponse.getCountryId() == null ) {
			return null;
		}
		teamResponse.setLeagueId(
				requestRestCallAndProcessResponse(countryName, teamResponse,Constant.GET_LEAGUES_URL, "country_name",
						"country_id", "{country-id}",teamResponse.getCountryId())
				);

		if(teamResponse.getLeagueId() == null ) {
			return null;
		}

		teamResponse.setTeamId(
				requestRestCallAndProcessResponse(countryName, teamResponse,Constant.GET_LEAGUES_URL, "team_name",
						"team_key", "{league-id}",teamResponse.getLeagueId())
				);

		if(teamResponse.getTeamId() == null ) {
			return null;
		}
		
		
		return teamResponse;
	}

	private String requestRestCallAndProcessResponse(String countryName, TeamResponse teamResponse, String url, 
			String entityName, String entityId, String toBeReplace, String replaceWith) {
		
		if(null != toBeReplace && null != replaceWith) {
			url = url.replace(toBeReplace, replaceWith);
		}

		RestCallUtil allentity = new RestCallUtil(url);
		String responseEntity = allentity.executeRestCall();
		JSONArray entityJsonArray = new JSONArray(responseEntity);

		for(Object object: entityJsonArray.toList()) {
			JSONObject jsonObject = (JSONObject)object;
			if(countryName.equalsIgnoreCase(jsonObject.optString(entityName))) {
				return jsonObject.optString(entityId);

			}
		}
		return null;
	}

}
