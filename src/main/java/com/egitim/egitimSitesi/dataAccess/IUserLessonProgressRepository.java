package com.egitim.egitimSitesi.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.egitim.egitimSitesi.entities.UserLessonProgress;

@Repository
public interface IUserLessonProgressRepository extends JpaRepository<UserLessonProgress, Integer> {

	Optional<UserLessonProgress> findByOurUserIdAndLessonId(int userId, int lessonId);
}
