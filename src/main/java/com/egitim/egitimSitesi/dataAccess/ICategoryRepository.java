package com.egitim.egitimSitesi.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egitim.egitimSitesi.entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
	
	Category findByName(String categoryName);

	boolean existsByName(String name);
}
