package com.egitim.egitimSitesi.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLessonsRequest {

		private int id;
		private String name;
		private String explanation;
		private int categoryId;
}
