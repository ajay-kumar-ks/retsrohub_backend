package com.grubtest2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grubtest2.Models.Comment;
import com.grubtest2.Models.Food;
import com.grubtest2.Models.Rating;
import com.grubtest2.Models.RatingRepository;
import com.grubtest2.Models.User;
import com.grubtest2.repository.FoodRepository;

@Service
public class RatingServiceImplementation implements RatingService{

	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FoodService foodService;
	
	@Autowired
	FoodRepository foodRepository;
	
	@Override
	public Rating createNewRating(Rating rating, Integer userId ,Integer FoodId) throws Exception {
		
		
		 User user = userService.findUserById(userId);
	        user.setId(userId);
	        
	        Food food = foodService.findFoodByFoodId(FoodId);
	        
	        food.setId(FoodId);
	        
	        rating.setUser(user);
//	        rating.setFood(food);
	        rating.setValue(rating.getValue());
	        
	        
//	        return ratingRepository.save(rating);
	        
	        

			Rating savedRating=ratingRepository.save(rating);
			
			food.getRating().add(savedRating);
		
			foodRepository.save(food);
			
			
			return savedRating;
	}

//	  @Override
//	    public List<Double> findRatingValuesByFoodId(Integer foodId) {
//	        List<Rating> ratings = ratingRepository.findRatingByFoodId(foodId);
//
//	        return ratings.stream()
//	                .map(Rating::getValue)
//	                .collect(Collectors.toList());
//	    }
//
//	@Override
//	public Rating findRatingByRateId(Integer rateId) {
//	
//		return ratingRepository.findById(rateId).orElse(null);
//	}
//	
//	@Override
//	public Double findAvarageRating(Integer foodId) {
//		return ratingRepository.calculateAverageRatingForFood(foodId);
//		
//	}

}
