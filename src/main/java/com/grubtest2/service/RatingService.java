package com.grubtest2.service;

import java.util.List;

import com.grubtest2.Models.Rating;
import com.grubtest2.exception.UserException;

public interface RatingService {

    Rating createNewRating(Rating rating, Integer userId,Integer FoodId) throws UserException, Exception;


//    Rating findRatingByRateId(Integer rateId);
//
//	List<Double> findRatingValuesByFoodId(Integer foodId);
//	
//	Double findAvarageRating(Integer foodId);
}
