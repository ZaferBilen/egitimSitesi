package com.egitim.egitimSitesi.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByLessonNameResponse {
	
	private String name;
	private String explanation;
}
