package com.egitim.egitimSitesi.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserFavoriteRequest {

	private int ourUserId;
    private int lessonId;
}