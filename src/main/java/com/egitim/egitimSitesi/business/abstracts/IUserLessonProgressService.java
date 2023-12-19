package com.egitim.egitimSitesi.business.abstracts;

public interface IUserLessonProgressService {

	 void updateLessonProgress(int userId, int lessonId, int watchTimeInSeconds);
	 int getWatchTime(int userId, int lessonId);
	 int getLastWatchedPosition(int userId, int lessonId);
}
