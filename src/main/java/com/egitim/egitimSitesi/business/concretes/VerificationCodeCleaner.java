package com.egitim.egitimSitesi.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.egitim.egitimSitesi.dataAccess.IOurUserRepository;
import com.egitim.egitimSitesi.entities.OurUser;

@Component
public class VerificationCodeCleaner {

    @Autowired
    private IOurUserRepository ourUserRepository;

    @Scheduled(fixedRate = 120000) // 120000 milliseconds = 2 minutes
    public void cleanExpiredVerificationCodes() {
        LocalDateTime now = LocalDateTime.now();
        List<OurUser> users = ourUserRepository.findAll();
        for (OurUser user : users) {
            if (user.getVerificationCodeExpiry().isBefore(now)) {
                user.setTemporaryVerificationCode(null);
                user.setVerificationCodeExpiry(null);
                ourUserRepository.save(user);
            }
        }
    }
}
