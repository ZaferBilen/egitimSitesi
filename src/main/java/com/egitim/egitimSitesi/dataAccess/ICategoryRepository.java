package com.egitim.egitimSitesi.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egitim.egitimSitesi.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

}
