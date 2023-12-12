package com.egitim.egitimSitesi.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMyDetailsResponse {

	private String email;
	private String password;
    private String roles;
}