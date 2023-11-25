package com.egitim.egitimSitesi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String name;
	
	private String explanation; //açıklama
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	
}
