package com.grubtest2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grubtest2.Models.Comment;
import com.grubtest2.Models.Food;
import com.grubtest2.Models.RestaurentComment;


public interface FoodService {
	
   Food createNewFood(Food food, Integer userId) throws Exception;
	
	String deleteFood(Integer foodId, Integer userId) throws Exception;
	
	List<Food> findFoodByRestroId(Integer restro);
	
	Food findFoodByFoodId(Integer foodId) throws Exception;
	
	List<Food> findAllFood();
	
	Food likeFood(Integer foodId, Integer userId) throws Exception;
	
	Food settingSpecialFood(Integer foodId) throws Exception;
	
	Food settingAvailability(Integer foodId) throws Exception;
	
	Comment createFoodComment(Comment comment, Integer foodId, Integer userId) throws Exception;
	
	public List<Object[]> findRestaurantsAndFoodByAvgRatingDesc();

//	Page<Food> getFoodsOrderByAvgRatingAndRatingCount();
	

}
