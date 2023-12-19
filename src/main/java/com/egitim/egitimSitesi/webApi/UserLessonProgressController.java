package com.egitim.egitimSitesi.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.egitimSitesi.business.abstracts.IUserLessonProgressService;

@RestController
@RequestMapping("/api/lesson-progress")
public class UserLessonProgressController {

    private IUserLessonProgressService userLessonProgressService;

    @Autowired
    public UserLessonProgressController(IUserLessonProgressService userLessonProgressService) {
        this.userLessonProgressService = userLessonProgressService;
    }

    @PutMapping("/{userId}/{lessonId}")
    public ResponseEntity<String> updateLessonProgress(
            @PathVariable int userId,
            @PathVariable int lessonId,
            @RequestBody int watchTimeInSeconds) {

        userLessonProgressService.updateLessonProgress(userId, lessonId, watchTimeInSeconds);
        return new ResponseEntity<>("Lesson progress updated successfully", HttpStatus.OK);
    }

    @GetMapping("/{userId}/{lessonId}/watch-time")
    public ResponseEntity<Integer> getWatchTime(
            @PathVariable int userId,
            @PathVariable int lessonId) {

        int watchTime = userLessonProgressService.getWatchTime(userId, lessonId);
        return new ResponseEntity<>(watchTime, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{lessonId}/last-watched-position") // Yeni eklenen endpoint
    public ResponseEntity<Integer> getLastWatchedPosition(
            @PathVariable int userId,
            @PathVariable int lessonId) {

        int lastWatchedPosition = userLessonProgressService.getLastWatchedPosition(userId, lessonId);
        return new ResponseEntity<>(lastWatchedPosition, HttpStatus.OK);
    }
}

