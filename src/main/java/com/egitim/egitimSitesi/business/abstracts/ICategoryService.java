package com.egitim.egitimSitesi.business.abstracts;

import java.util.List;

import com.egitim.egitimSitesi.business.requests.CreateCategoryRequest;
import com.egitim.egitimSitesi.business.requests.UpdateCategoryRequest;
import com.egitim.egitimSitesi.business.responses.AdminGetAllCategoryResponse;
import com.egitim.egitimSitesi.business.responses.GetAllCategoryResponse;
import com.egitim.egitimSitesi.business.responses.GetLessonByCategoryResponse;

public interface ICategoryService {
	
	List<GetAllCategoryResponse> GetAllCategoryResponse();
	List<AdminGetAllCategoryResponse> adminGetAllCategoryResponse();
	List<GetLessonByCategoryResponse> getLessonByCategoryResponse(String categoryName);
	void add(CreateCategoryRequest createCategoryRequest);
	void update(UpdateCategoryRequest updateCategoryRequest);
	void delete(int id);
}
