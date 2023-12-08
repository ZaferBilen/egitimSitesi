package com.egitim.egitimSitesi.business.abstracts;

import java.util.List;

import com.egitim.egitimSitesi.business.requests.AddUserFavoriteRequest;
import com.egitim.egitimSitesi.business.requests.RemoveUserFavoriteRequest;
import com.egitim.egitimSitesi.entities.UserFavorite;

public interface IUserFavoriteService {
	void addFavoriteLessonToUser(AddUserFavoriteRequest addUserFavoriteRequest);
	void removeFavoriteLessonFromUser(RemoveUserFavoriteRequest removeUserFavoriteRequest);
	List<UserFavorite> getAllFavoriteLessonsByUser(int ourUserId);
}
