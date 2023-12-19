package com.egitim.egitimSitesi.business.concretes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.business.abstracts.IUserFavoriteService;
import com.egitim.egitimSitesi.business.requests.AddUserFavoriteRequest;
import com.egitim.egitimSitesi.business.requests.RemoveUserFavoriteRequest;
import com.egitim.egitimSitesi.business.responses.GetUserFavoriteResponse;
import com.egitim.egitimSitesi.core.utilities.mappers.IModelMapperService;
import com.egitim.egitimSitesi.dataAccess.IOurUserRepository;
import com.egitim.egitimSitesi.dataAccess.IUserFavoriteRepository;
import com.egitim.egitimSitesi.entities.OurUser;
import com.egitim.egitimSitesi.entities.UserFavorite;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserFavoriteManager implements IUserFavoriteService {


    private IUserFavoriteRepository userFavoriteRepository;
    private IOurUserRepository userRepository;
	private IModelMapperService modelMapperService;
    
	@Override
	public void addFavoriteLessonToUser(AddUserFavoriteRequest addUserFavoriteRequest) {
	    
		ModelMapper modelMapper = modelMapperService.forRequest();
        
	    UserFavorite userFavorite = modelMapper.map(addUserFavoriteRequest, UserFavorite.class);
        userFavoriteRepository.save(userFavorite);
    }

	@Override
	public void removeFavoriteLessonFromUser(RemoveUserFavoriteRequest removeUserFavoriteRequest) {
		
		ModelMapper modelMapper = modelMapperService.forRequest();
        
		UserFavorite userFavorite = modelMapper.map(removeUserFavoriteRequest, UserFavorite.class);
        userFavoriteRepository.delete(userFavorite);
		
	}

	@Override
	public List<GetUserFavoriteResponse> getAllFavoriteLessonsByUser(int ourUserId) {
	    OurUser user = userRepository.findById(ourUserId).orElse(null);
	    if (user != null) {
	        List<GetUserFavoriteResponse> favoriteResponses = new ArrayList<>();
	        for (UserFavorite userFavorite : user.getFavoriteLessons()) {
	            favoriteResponses.add(new GetUserFavoriteResponse(userFavorite.getLesson().getName()));
	        }
	        return favoriteResponses;
	    }
	    return Collections.emptyList();
	}

}
