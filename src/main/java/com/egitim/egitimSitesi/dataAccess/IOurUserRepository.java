package com.egitim.egitimSitesi.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.egitim.egitimSitesi.entities.OurUser;

@Repository
public interface IOurUserRepository extends JpaRepository<OurUser, Integer> {
    @Query(value = "select * from ourusers where email = ?1", nativeQuery = true)
    Optional<OurUser> findByEmail(String email);
    
}


