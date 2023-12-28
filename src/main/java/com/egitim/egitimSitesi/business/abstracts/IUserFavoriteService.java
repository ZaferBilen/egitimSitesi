package com.egitim.egitimSitesi.business.abstracts;

import java.util.List;

import com.egitim.egitimSitesi.business.requests.AddUserFavoriteRequest;
import com.egitim.egitimSitesi.business.responses.GetUserFavoriteResponse;

public interface IUserFavoriteService {
	void addFavoriteLessonToUser(AddUserFavoriteRequest addUserFavoriteRequest);
	//void removeFavoriteLessonFromUser(RemoveUserFavoriteRequest removeUserFavoriteRequest);
	List<GetUserFavoriteResponse> getAllFavoriteLessonsByUser(int ourUserId);
	void removeFavoriteLesson(int ourUserId, int lessonId);
}