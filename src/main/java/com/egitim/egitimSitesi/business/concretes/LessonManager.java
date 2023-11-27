package com.egitim.egitimSitesi.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.business.abstracts.ILessonService;
import com.egitim.egitimSitesi.business.requests.CreateLessonsRequest;
import com.egitim.egitimSitesi.business.requests.UpdateLessonsRequest;
import com.egitim.egitimSitesi.business.responses.AdminGetAllLessonsResponse;
import com.egitim.egitimSitesi.business.responses.GetAllLessonsResponse;
import com.egitim.egitimSitesi.core.utilities.mappers.IModelMapperService;
import com.egitim.egitimSitesi.dataAccess.ILessonRepository;
import com.egitim.egitimSitesi.entities.Lesson;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonManager implements ILessonService{
	
	private ILessonRepository lessonRepository;
	private IModelMapperService modelMapperService;
	

	@Override
	public List<GetAllLessonsResponse> getAllLessonsResponse() {
		List<Lesson> lessons = lessonRepository.findAll();
		
		List<GetAllLessonsResponse> lessonsResponse = lessons.stream()
				.map(lesson ->this.modelMapperService.forResponse()
						.map(lesson, GetAllLessonsResponse.class)).collect(Collectors.toList());	
		return lessonsResponse;
	}

	
	@Override
	public List<AdminGetAllLessonsResponse> adminGetAllLessonsResponse() {
		List<Lesson> lessons = lessonRepository.findAll();
		
		List<AdminGetAllLessonsResponse> lessonsResponse = lessons.stream()
				.map(lesson ->this.modelMapperService.forResponse()
						.map(lesson, AdminGetAllLessonsResponse.class)).collect(Collectors.toList());	
		return lessonsResponse;
	}
	

	
	
	@Override
	public void add(CreateLessonsRequest createLessonsRequest) {
		Lesson lesson = this.modelMapperService.forRequest()
				.map(createLessonsRequest, Lesson.class);
		
		this.lessonRepository.save(lesson);
		
	}
	
	@Override
	public void update(UpdateLessonsRequest updateLessonsRequest) {
		Lesson lesson = this.modelMapperService.forRequest()
				.map(updateLessonsRequest, Lesson.class);
		
		this.lessonRepository.save(lesson);
		
	}

	@Override
	public void delete(int id) {
		
		this.lessonRepository.deleteById(id);	
	}



}
