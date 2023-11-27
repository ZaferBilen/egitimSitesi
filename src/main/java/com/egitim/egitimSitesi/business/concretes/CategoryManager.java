package com.egitim.egitimSitesi.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.business.abstracts.ICategoryService;
import com.egitim.egitimSitesi.business.requests.CreateCategoryRequest;
import com.egitim.egitimSitesi.business.requests.UpdateCategoryRequest;
import com.egitim.egitimSitesi.business.responses.AdminGetAllCategoryResponse;
import com.egitim.egitimSitesi.business.responses.GetAllCategoryResponse;
import com.egitim.egitimSitesi.business.responses.GetLessonByCategoryResponse;
import com.egitim.egitimSitesi.core.utilities.mappers.IModelMapperService;
import com.egitim.egitimSitesi.dataAccess.ICategoryRepository;
import com.egitim.egitimSitesi.entities.Category;
import com.egitim.egitimSitesi.entities.Lesson;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryManager implements ICategoryService {

	
	private ICategoryRepository categoryRepository;
	private IModelMapperService modelMapperService;
	
	@Override
	public List<GetAllCategoryResponse> GetAllCategoryResponse() {
		List<Category> categories = categoryRepository.findAll();
		
		List<GetAllCategoryResponse> categoryResponse= categories.stream()
				.map(category ->this.modelMapperService.forResponse()
						.map(category, GetAllCategoryResponse.class)).collect(Collectors.toList());
		
		return categoryResponse;
	}
	

	@Override
	public List<AdminGetAllCategoryResponse> adminGetAllCategoryResponse() {
		List<Category> categories = categoryRepository.findAll();
		
		List<AdminGetAllCategoryResponse> categoryResponse = categories.stream()
				.map(category -> this.modelMapperService.forResponse()
						.map(category, AdminGetAllCategoryResponse.class)).collect(Collectors.toList());
		
		return categoryResponse;
	}
	
	
	@Override
	public List<GetLessonByCategoryResponse> getLessonByCategoryResponse(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        
        List<Lesson> lessons = category.getLessons();
        
        List<GetLessonByCategoryResponse> response = lessons.stream()
        		.map(lesson -> this.modelMapperService.forResponse()
        				.map(lesson, GetLessonByCategoryResponse.class)).collect(Collectors.toList());
        
        return response;
	}
	


	@Override
	public void add(CreateCategoryRequest createCategoryRequest) {
		
		Category category=this.modelMapperService.forRequest()
				.map(createCategoryRequest, Category.class);
		this.categoryRepository.save(category);
		
	}

	@Override
	public void update(UpdateCategoryRequest updateCategoryRequest) {
		
		Category category=this.modelMapperService.forRequest()
				.map(updateCategoryRequest, Category.class);
		this.categoryRepository.save(category);
		
	}

	@Override
	public void delete(int id) {
		
		this.categoryRepository.deleteById(id);
		
	}



}
