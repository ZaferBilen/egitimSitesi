package com.egitim.egitimSitesi.business.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.egitim.egitimSitesi.business.abstracts.IUserFavoriteService;
import com.egitim.egitimSitesi.business.requests.AddUserFavoriteRequest;
import com.egitim.egitimSitesi.business.requests.RemoveUserFavoriteRequest;
import com.egitim.egitimSitesi.core.utilities.mappers.IModelMapperService;
import com.egitim.egitimSitesi.dataAccess.IUserFavoriteRepository;
import com.egitim.egitimSitesi.entities.UserFavorite;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserFavoriteManager implements IUserFavoriteService {


    private IUserFavoriteRepository userFavoriteRepository;
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
	public List<UserFavorite> getAllFavoriteLessonsByUser(int ourUserId) {
	
		 return userFavoriteRepository.findAllByOurUserId(ourUserId);
	}

}
