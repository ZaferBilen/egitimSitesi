package com.egitim.egitimSitesi.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequests {

	 private String email;
	 private String password;
	 private String roles;
	 private String verificationCode;
}
