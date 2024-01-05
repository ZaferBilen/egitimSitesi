package com.egitim.egitimSitesi.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.egitim.egitimSitesi.business.requests.RegisterUserRequests;
import com.egitim.egitimSitesi.business.responses.GetAllUserResponse;
import com.egitim.egitimSitesi.business.responses.GetMyDetailsResponse;

public interface IOurUserService {

	void sendVerificationCode(String email);

    List<GetAllUserResponse> getAllUsers();
   
    Optional<GetMyDetailsResponse> findByEmail(String email);
    
    void delete(int id);
    
    boolean userRegistration(RegisterUserRequests registerUserRequests);
    
    boolean isValidPassword(String password);
    
}