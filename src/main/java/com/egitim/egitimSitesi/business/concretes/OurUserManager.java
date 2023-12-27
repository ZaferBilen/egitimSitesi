package com.egitim.egitimSitesi.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.business.abstracts.IEmailService;
import com.egitim.egitimSitesi.business.abstracts.IOurUserService;
import com.egitim.egitimSitesi.business.requests.RegisterUserRequests;
import com.egitim.egitimSitesi.business.responses.GetAllUserResponse;
import com.egitim.egitimSitesi.business.responses.GetMyDetailsResponse;
import com.egitim.egitimSitesi.dataAccess.IOurUserRepository;
import com.egitim.egitimSitesi.entities.OurUser;
import com.egitim.egitimSitesi.util.VerificationCodeCache;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OurUserManager implements IOurUserService {

    @Autowired
    private IOurUserRepository ourUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private IEmailService emailService;

    @Override
    public void sendVerificationCode(String email) {
        String verificationCode = generateVerificationCode();
        VerificationCodeCache.saveVerificationCode(email, verificationCode);

        // E-posta gönderme işlemi
        emailService.sendVerificationCode(email, verificationCode);
    }

    @Override
    public boolean completeUserRegistration(RegisterUserRequests registerUserRequests) {
        String email = registerUserRequests.getEmail();
        String verificationCode = registerUserRequests.getVerificationCode();

        String cachedCode = VerificationCodeCache.getVerificationCode(email);
        
        if (cachedCode != null && cachedCode.equals(verificationCode)) {
            // Kod doğruysa ve henüz süresi geçmemişse
            VerificationCodeCache.removeVerificationCode(email); // Kodu hafızadan sil
            OurUser ourUser = new OurUser();
            ourUser.setEmail(email);
            ourUser.setPassword(passwordEncoder.encode(registerUserRequests.getPassword()));
            ourUser.setRoles(registerUserRequests.getRoles());

            ourUserRepository.save(ourUser); 

            return true; 
        }
        
        return false; 
    }

    private String generateVerificationCode() {
        return UUID.randomUUID().toString().substring(0, 6); // Örneğin 6 karakterlik bir kod
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