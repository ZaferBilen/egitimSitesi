package com.egitim.egitimSitesi.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.egitim.egitimSitesi.business.requests.RegisterUserRequests;
import com.egitim.egitimSitesi.business.responses.GetAllUserResponse;
import com.egitim.egitimSitesi.business.responses.GetMyDetailsResponse;
import com.egitim.egitimSitesi.entities.OurUser;

public interface IOurUserService {

    OurUser saveUser(RegisterUserRequests registerUserRequests);

    List<GetAllUserResponse> getAllUsers();
   
    Optional<GetMyDetailsResponse> findByEmail(String email);
}