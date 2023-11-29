package com.egitim.egitimSitesi.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.business.abstracts.IOurUserService;
import com.egitim.egitimSitesi.dataAccess.IOurUserRepository;
import com.egitim.egitimSitesi.entities.OurUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OurUserManager implements IOurUserService {

    @Autowired
    private IOurUserRepository ourUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OurUser saveUser(OurUser ourUser){
        ourUser.setPassword(passwordEncoder.encode(ourUser.getPassword()));
        return ourUserRepository.save(ourUser);
    }

    @Override
    public List<OurUser> getAllUsers(){
        return ourUserRepository.findAll();
    }

    @Override
    public OurUser getMyDetails(String username){
        Optional<OurUser> ourUserOptional = ourUserRepository.findByEmail(username);
        if(ourUserOptional.isPresent()){
            return ourUserOptional.get();
        } 
        else {
            return null;
        }
    }

    
    
}