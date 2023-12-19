package com.egitim.egitimSitesi.business.concretes;

import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.business.abstracts.IUserLessonProgressService;
import com.egitim.egitimSitesi.dataAccess.ILessonRepository;
import com.egitim.egitimSitesi.dataAccess.IOurUserRepository;
import com.egitim.egitimSitesi.dataAccess.IUserLessonProgressRepository;
import com.egitim.egitimSitesi.entities.Lesson;
import com.egitim.egitimSitesi.entities.OurUser;
import com.egitim.egitimSitesi.entities.UserLessonProgress;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserLessonProgressManager implements IUserLessonProgressService {

    private IUserLessonProgressRepository userLessonProgressRepository;
    private IOurUserRepository ourUserRepository;
    private ILessonRepository lessonRepository;

    @Override
    public void updateLessonProgress(int userId, int lessonId, int watchTimeInSeconds) {
        OurUser ourUser = ourUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with ID: " + lessonId));

        UserLessonProgress userLessonProgress = userLessonProgressRepository
                .findByOurUserIdAndLessonId(userId, lessonId)
                .orElse(new UserLessonProgress());

        userLessonProgress.setLastWatchedTimeInSeconds(watchTimeInSeconds);
        userLessonProgress.setLastWatchedPositionInSeconds(watchTimeInSeconds); // Yeni eklenen satır

        userLessonProgress.setOurUser(ourUser);
        userLessonProgress.setLesson(lesson);
        userLessonProgress.setWatchTimeInSeconds(watchTimeInSeconds);

        userLessonProgressRepository.save(userLessonProgress);
    }

    @Override
    public int getWatchTime(int userId, int lessonId) {
        UserLessonProgress userLessonProgress = userLessonProgressRepository
                .findByOurUserIdAndLessonId(userId, lessonId)
                .orElse(new UserLessonProgress());

        return userLessonProgress.getWatchTimeInSeconds();
    }

    @Override
    public int getLastWatchedPosition(int userId, int lessonId) {
        UserLessonProgress userLessonProgress = userLessonProgressRepository
                .findByOurUserIdAndLessonId(userId, lessonId)
                .orElse(new UserLessonProgress());

        return userLessonProgress.getLastWatchedPositionInSeconds();
    }
}

