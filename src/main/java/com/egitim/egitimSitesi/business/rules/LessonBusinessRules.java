package com.egitim.egitimSitesi.business.rules;

import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.core.utilities.exception.BusinessException;
import com.egitim.egitimSitesi.dataAccess.ILessonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LessonBusinessRules {

	private ILessonRepository lessonRepository;
	
	public void checkIfLessonNameExists(String name) {
		if(this.lessonRepository.existsByName(name)) {
			throw new BusinessException("Lesson name already exists: " + name);
		}
	}
}
