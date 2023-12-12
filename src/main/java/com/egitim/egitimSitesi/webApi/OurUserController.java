package com.egitim.egitimSitesi.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.egitimSitesi.business.abstracts.IOurUserService;
import com.egitim.egitimSitesi.business.requests.RegisterUserRequests;
import com.egitim.egitimSitesi.entities.OurUser;

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
    public ResponseEntity<Object> saveUser(@RequestBody RegisterUserRequests  registerUserRequests){
        OurUser result = ourUserService.saveUser(registerUserRequests);
        if (result.getId() > 0){
            return ResponseEntity.ok("User Was Saved");
        }
        return ResponseEntity.status(404).body("Error, User Not Saved");
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
    
    
}

