package com.egitim.egitimSitesi.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egitim.egitimSitesi.entities.Lesson;

@Repository
public interface ILessonRepository extends JpaRepository<Lesson, Integer>{
	
	
	List<Lesson> findByName(String lessonName);
	
	boolean existsByName(String name);

}
