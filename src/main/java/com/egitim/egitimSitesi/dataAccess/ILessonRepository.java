package com.egitim.egitimSitesi.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egitim.egitimSitesi.entities.Lesson;

public interface ILessonRepository extends JpaRepository<Lesson, Integer>{
	
	
	List<Lesson> findByName(String lessonName);
	
}
