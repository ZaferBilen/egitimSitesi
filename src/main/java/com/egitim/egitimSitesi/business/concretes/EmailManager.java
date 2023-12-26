package com.egitim.egitimSitesi.business.concretes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.business.abstracts.IEmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailManager implements IEmailService {
	
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public void sendVerificationCode(String toEmail, String verificationCode) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Verification Code for Registration");
            helper.setText("Your verification code is: " + verificationCode);

            mailSender.send(message);
        } catch (MessagingException e) {        
            e.printStackTrace();
        }
    }
}