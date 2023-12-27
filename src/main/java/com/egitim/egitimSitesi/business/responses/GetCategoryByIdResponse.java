package com.egitim.egitimSitesi.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryByIdResponse {
	
	private String categoryName;
	private String lessonName;
}
