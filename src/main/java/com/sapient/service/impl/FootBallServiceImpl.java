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
				requestRestCallAndProcessResponse(countryName, Constant.GET_COUNTRY_URL, 
						"country_name", "country_id",null, null)
				);


		if(teamResponse.getCountryId() == null ) {
			return null;
		}
		teamResponse.setLeagueId(
				requestRestCallAndProcessResponse(leagueName, Constant.GET_LEAGUES_URL, "country_name",
						"country_id", "{country-id}",teamResponse.getCountryId())
				);

		if(teamResponse.getLeagueId() == null ) {
			return null;
		}

		teamResponse.setTeamId(
				requestRestCallAndProcessResponse(teamName, Constant.GET_LEAGUES_URL, "team_name",
						"team_key", "{league-id}",teamResponse.getLeagueId())
				);

		if(teamResponse.getTeamId() == null ) {
			return null;
		}
		
		teamResponse.setOverallPosition(
				requestRestCallAndProcessResponse(teamResponse.getTeamId() ,Constant.GET_STATISTICS_URL, "team_id",
						"overall_league_position", "{league-id}",teamResponse.getLeagueId())
				);
		return teamResponse;
	}

	private String requestRestCallAndProcessResponse(String entityValue,  String url, 
			String entityName, String entityId, String toBeReplace, String replaceWith) {
		
		if(null != toBeReplace && null != replaceWith) {
			url = url.replace(toBeReplace, replaceWith);
		}

		RestCallUtil allentity = new RestCallUtil(url);
		String responseEntity = allentity.executeRestCall();
		JSONArray entityJsonArray = new JSONArray(responseEntity);

		for(int i = 0; i< entityJsonArray.length(); i++) {
			JSONObject jsonObject = entityJsonArray.optJSONObject(i);
			if(entityValue.equalsIgnoreCase(jsonObject.optString(entityName))) {
				return jsonObject.optString(entityId);

			}
		}
		return null;
	}

}
