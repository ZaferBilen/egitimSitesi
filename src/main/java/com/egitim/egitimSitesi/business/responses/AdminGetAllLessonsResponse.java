package com.egitim.egitimSitesi.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminGetAllLessonsResponse {

	
	private int id;
	
	private String name;
	
	private String explanation;
	
	private String categoryId;
	
	private String categoryName;
	
}
