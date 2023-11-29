package com.egitim.egitimSitesi.business.rules;

import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.core.utilities.exception.BusinessException;
import com.egitim.egitimSitesi.dataAccess.ICategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryBusinessRules {
	
	private ICategoryRepository categoryRepository;
	
	public void checkIfCategoryNameExists(String name) {
		if(this.categoryRepository.existsByName(name)) {
			throw new BusinessException("Category name already exists");
		}
	}
}