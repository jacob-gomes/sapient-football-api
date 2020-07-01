package com.sapient.validate;

import com.sapient.exception.BadRequestException;

public class Validate {
	private Validate() {}

	public static void requestForGetRecord(String countryName, String leagueName, String teamName) throws BadRequestException {
		if(null == countryName || countryName.trim().isEmpty()
			||	null == leagueName || leagueName.trim().isEmpty()
			||	null == teamName || teamName.trim().isEmpty()) {
			throw new BadRequestException("request params cannot be empty");
		}
	}
	
	
}
