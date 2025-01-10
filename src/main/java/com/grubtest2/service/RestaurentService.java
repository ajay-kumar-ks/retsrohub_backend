package com.grubtest2.service;

import java.util.List;

import com.grubtest2.Models.Rating;
import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.User;
import com.grubtest2.exception.UserException;

import org.springframework.stereotype.Service;

@Service
public interface RestaurentService {

	
    public Restaurent registerRestaurent(Restaurent restaurent, Integer userId) throws UserException;
	
	public Restaurent findRestaurentById(Integer restaurentId) throws Exception;
	
	public Restaurent findRestaurentByUserId(Integer userId);
	
	public Restaurent upadateRestaurent(Restaurent restaurent, Integer restaurentId) throws UserException;
	
	public List<Restaurent> searchRestaurent(String query);
	
	public Rating createNewRating(Rating rating, Integer userId,Integer restroId) throws UserException, Exception;



	
}
