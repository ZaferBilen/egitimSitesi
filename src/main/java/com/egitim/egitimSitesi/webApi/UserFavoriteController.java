package com.egitim.egitimSitesi.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.egitimSitesi.business.abstracts.IUserFavoriteService;
import com.egitim.egitimSitesi.business.requests.AddUserFavoriteRequest;
import com.egitim.egitimSitesi.business.requests.RemoveUserFavoriteRequest;
import com.egitim.egitimSitesi.business.responses.GetUserFavoriteResponse;

@RestController
@RequestMapping("/api/user-favorites")
public class UserFavoriteController {

    private IUserFavoriteService userFavoriteService;

    public UserFavoriteController(IUserFavoriteService userFavoriteService) {
        this.userFavoriteService = userFavoriteService;
    }

    @PostMapping("/add")
    public void addFavoriteLessonToUser(@RequestBody AddUserFavoriteRequest addUserFavoriteRequest) {
        userFavoriteService.addFavoriteLessonToUser(addUserFavoriteRequest);
    }

    @PostMapping("/remove")
    public void removeFavoriteLessonFromUser(@RequestBody RemoveUserFavoriteRequest removeUserFavoriteRequest) {
        userFavoriteService.removeFavoriteLessonFromUser(removeUserFavoriteRequest);
    }

    @GetMapping("/user/{ourUserId}")
    public List<GetUserFavoriteResponse> getAllFavoriteLessonsByUser(@PathVariable int ourUserId) {
        return userFavoriteService.getAllFavoriteLessonsByUser(ourUserId);
    }
}