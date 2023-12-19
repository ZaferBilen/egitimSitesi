package com.egitim.egitimSitesi.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.business.abstracts.IOurUserService;
import com.egitim.egitimSitesi.business.requests.RegisterUserRequests;
import com.egitim.egitimSitesi.business.responses.GetAllUserResponse;
import com.egitim.egitimSitesi.business.responses.GetMyDetailsResponse;
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
    public OurUser saveUser(RegisterUserRequests registerUserRequests){
        OurUser ourUser = new OurUser();
        ourUser.setEmail(registerUserRequests.getEmail());
        ourUser.setPassword(passwordEncoder.encode(registerUserRequests.getPassword()));
        ourUser.setRoles(registerUserRequests.getRoles());

        return ourUserRepository.save(ourUser);
    }

    @Override
    public List<GetAllUserResponse> getAllUsers(){
        List<OurUser> users = ourUserRepository.findAll();
        List<GetAllUserResponse> userResponses = new ArrayList<>();

        for (OurUser user : users) {
            GetAllUserResponse response = new GetAllUserResponse();
            response.setId(user.getId());
            response.setEmail(user.getEmail());
            response.setPassword(user.getPassword());
            response.setRoles(user.getRoles());
            

            userResponses.add(response);
        }

        return userResponses;
    }

    @Override
    public Optional<GetMyDetailsResponse> findByEmail(String email) {
        Optional<OurUser> userOptional = ourUserRepository.findByEmail(email);
        return userOptional.map(user -> new GetMyDetailsResponse(user.getEmail(), user.getRoles()));
    }

	@Override
	public void delete(int id) {
		
		this.ourUserRepository.deleteById(id);
		
	}
   
    
}