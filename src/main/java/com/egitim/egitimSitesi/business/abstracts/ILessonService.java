package com.egitim.egitimSitesi.business.abstracts;

import java.util.List;

import com.egitim.egitimSitesi.business.requests.CreateLessonsRequest;
import com.egitim.egitimSitesi.business.requests.UpdateLessonsRequest;
import com.egitim.egitimSitesi.business.responses.AdminGetAllLessonsResponse;
import com.egitim.egitimSitesi.business.responses.GetAllLessonsResponse;
import com.egitim.egitimSitesi.business.responses.GetByLessonNameResponse;

public interface ILessonService {

	List<GetAllLessonsResponse> getAllLessonsResponse();
	List<GetByLessonNameResponse> getByLessonNameResponse(String lessonName);
	List<AdminGetAllLessonsResponse> adminGetAllLessonsResponse();
	void add(CreateLessonsRequest createLessonsRequest);
	void update(UpdateLessonsRequest updateLessonsRequest);
	void delete(int id);
}
