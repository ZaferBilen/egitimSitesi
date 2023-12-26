package com.egitim.egitimSitesi.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "ourusers")
public class OurUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    private String roles;
    @Transient
    private String temporaryVerificationCode;

    @Transient
    private LocalDateTime verificationCodeExpiry;
    private boolean verified;
    
    @OneToMany(mappedBy = "ourUser", cascade = CascadeType.ALL)
    private List<UserFavorite> favoriteLessons = new ArrayList<>();

}
