package com.egitim.egitimSitesi.business.abstracts;

public interface IEmailService {

	void sendVerificationCode(String toEmail, String verificationCode);
}
