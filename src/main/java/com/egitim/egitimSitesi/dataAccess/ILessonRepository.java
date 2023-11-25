package com.egitim.egitimSitesi.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egitim.egitimSitesi.entities.Category;
import com.egitim.egitimSitesi.entities.Lesson;

public interface ILessonRepository extends JpaRepository<Lesson, Integer>{
	
	List<Lesson> findByCategory(Category category);
	List<Lesson> findByName(String lessonName);
}
