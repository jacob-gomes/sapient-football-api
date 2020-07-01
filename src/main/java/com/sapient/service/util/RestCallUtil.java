package com.sapient.service.util;

import org.springframework.web.client.RestTemplate;

public class RestCallUtil{
	
	private static RestTemplate restTemplate = new RestTemplate();
	private String url;
	
	public RestCallUtil(String url) {
		this.url = url;
	}
	
	public  String executeRestCall() {
		return restTemplate.getForObject(url, String.class);
	}
	
}
