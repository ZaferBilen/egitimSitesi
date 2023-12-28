package com.egitim.egitimSitesi.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.egitim.egitimSitesi.business.responses.GetUserFavoriteResponse;
import com.egitim.egitimSitesi.entities.UserFavorite;

@Repository
public interface IUserFavoriteRepository extends JpaRepository<UserFavorite, Integer>{
	List<GetUserFavoriteResponse> findAllByOurUserId(int ourUserId);
	
	@Query("delete from UserFavorite u where u.ourUser.id = :ourUserId and u.lesson.id = :lessonId")
	void removeFavoriteLesson(int ourUserId, int lessonId);
	
	UserFavorite findByOurUserIdAndLessonId(int ourUserId, int lessonId);
	
	boolean existsById (int id);
}