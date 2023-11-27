package com.egitim.egitimSitesi.business.abstracts;

import java.util.List;

import com.egitim.egitimSitesi.entities.OurUser;

public interface IOurUserService {

    OurUser saveUser(OurUser ourUser);

    List<OurUser> getAllUsers();

    OurUser getMyDetails(String username);
   
}