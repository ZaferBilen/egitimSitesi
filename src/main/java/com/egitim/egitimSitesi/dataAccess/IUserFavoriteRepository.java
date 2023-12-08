package com.egitim.egitimSitesi.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egitim.egitimSitesi.entities.UserFavorite;

@Repository
public interface IUserFavoriteRepository extends JpaRepository<UserFavorite, Integer>{
	List<UserFavorite> findAllByOurUserId(int ourUserId);
}
