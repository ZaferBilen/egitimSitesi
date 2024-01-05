package com.egitim.egitimSitesi.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.egitimSitesi.business.abstracts.IOurUserService;
import com.egitim.egitimSitesi.business.requests.RegisterUserRequests;

@RestController
@RequestMapping
public class OurUserController {

    @Autowired
    private IOurUserService ourUserService;
    

    @GetMapping("/")
    public String goHome(){
        return "This is publicly accessible without needing authentication.";
    }

    @PostMapping("/user/save")
    public ResponseEntity<Object> sendVerificationCode(@RequestParam String email){
        ourUserService.sendVerificationCode(email);
        return ResponseEntity.ok("Verification code has been sent to your email.");
    }

    @PostMapping("/user/save-verify")
    public ResponseEntity<Object> completeUserRegistration(@RequestBody RegisterUserRequests registerUserRequests){
    	String password = registerUserRequests.getPassword();

        if (!ourUserService.isValidPassword(password)) {
            return ResponseEntity.badRequest().body("Your password must be at least 6 characters long and contain one uppercase letter, one lowercase letter and one number.");
        }
        boolean isRegistered = ourUserService.userRegistration(registerUserRequests);
        if (isRegistered) {
            return ResponseEntity.ok("User registration completed successfully.");
        } else {
            return ResponseEntity.status(400).body("Invalid verification code or registration failed.");
        }
    }

    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers(){
        return ResponseEntity.ok(ourUserService.getAllUsers());
    }

    @GetMapping("/users/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getMyDetails(){
        return ResponseEntity.ok(ourUserService.findByEmail(getLoggedInUserDetails().getUsername()));
    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
    
    @DeleteMapping("/deleteuser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
    	ourUserService.delete(id);
        return ResponseEntity.ok("User Deleted");
    }
    
    
    
    
}

