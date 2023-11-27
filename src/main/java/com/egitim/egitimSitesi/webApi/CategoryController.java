package com.egitim.egitimSitesi.webApi;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.egitimSitesi.business.abstracts.ICategoryService;
import com.egitim.egitimSitesi.business.requests.CreateCategoryRequest;
import com.egitim.egitimSitesi.business.requests.UpdateCategoryRequest;
import com.egitim.egitimSitesi.business.responses.AdminGetAllCategoryResponse;
import com.egitim.egitimSitesi.business.responses.GetAllCategoryResponse;
import com.egitim.egitimSitesi.business.responses.GetLessonByCategoryResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/categories")  
@AllArgsConstructor
public class CategoryController{
	
	private ICategoryService categoryService;

	
	
	@GetMapping("/getallcategory")
	public List<GetAllCategoryResponse> getAllCategoryResponse(){
		
		return categoryService.GetAllCategoryResponse();
	}

	@GetMapping("/admin/getallcategories")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AdminGetAllCategoryResponse>> getAdminAllLessons() {
        List<AdminGetAllCategoryResponse> categories = categoryService.adminGetAllCategoryResponse();
        return ResponseEntity.ok(categories);
    }
	
	@GetMapping("/byCategory/{categoryName}")
	    public ResponseEntity<List<GetLessonByCategoryResponse>> getLessonsByCategory(@PathVariable String categoryName) {
		
		 List<GetLessonByCategoryResponse> lessonResponses = categoryService.getLessonByCategoryResponse(categoryName);
		 
		 return new ResponseEntity<>(lessonResponses, HttpStatus.OK);
	}
	

	@PostMapping("/addcategory")
	@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addLesson(@RequestBody CreateCategoryRequest createCategoryRequest) {
        categoryService.add(createCategoryRequest);
        return ResponseEntity.ok("Category Added");
    }
	

    @PutMapping("/updatecategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        categoryService.update(updateCategoryRequest);
      
        return ResponseEntity.ok("Category Updated");
    }

    
    @DeleteMapping("/deletecategory/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Category Deleted");
    }
	
	
	
	
	
	
	
	
	
	
	
}
