package com.grubtest2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grubtest2.Models.Comment;
import com.grubtest2.Models.Food;
import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.User;
import com.grubtest2.repository.CommentRepository;
import com.grubtest2.repository.FoodRepository;

@Service
public class FoodServiceImplementation implements FoodService {
	
	@Autowired
	RestaurentService restroService;
	
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public Food createNewFood(Food food, Integer userId) throws Exception {


		 Restaurent restroId = restroService.findRestaurentByUserId(userId);
	        
			
			Food newFood=new Food();
			newFood.setCaption(food.getCaption());
			newFood.setImage(food.getImage());
			newFood.setName(food.getName());
			newFood.setAvailability("av");
			newFood.setPrice(food.getPrice());
			newFood.setCreatedAt(LocalDateTime.now());
			newFood.setRestaurent(restroId);
			newFood.setAvailability(food.getAvailability());
			
			return foodRepository.save(newFood);
	}

	@Override
	public String deleteFood(Integer foodId, Integer userId) throws Exception {
	
		Food restroFood = findFoodByFoodId(foodId);
		Restaurent restro = restroService.findRestaurentByUserId(userId);
		
		if(restroFood.getId()!=restro.getId()) {
			
			throw new Exception("you cant delete others post");
		}
		
		foodRepository.delete(restroFood);
		return "Food is deleted";
	}

	@Override
	public List<Food> findFoodByRestroId(Integer restro) {
		return foodRepository.findFoodByRestroId(restro);
	}

	@Override
	public Food findFoodByFoodId(Integer foodId) throws Exception {
		
		Optional<Food> opt=foodRepository.findById(foodId);
		if(opt.isEmpty())
		{
			throw new Exception("food not found id "+ foodId);
		}
		return opt.get();
		
	}

	@Override
	public List<Food> findAllFood() {
	
		return foodRepository.findAll();
	}

	@Override
	public Food likeFood(Integer foodId, Integer userId) throws Exception {
		
		Food food = findFoodByFoodId(foodId);
		User user = userService.findUserById(userId);
		
		if(food.getLiked().contains(user)) {
			
			food.getLiked().remove(user);
		}else {
			
			food.getLiked().add(user);
		}
		
		
		
		return foodRepository.save(food);
	}
	
	public Food settingSpecialFood(Integer foodId) throws Exception {
		
		Food food = findFoodByFoodId(foodId);
		
		if(food.getIsSpecial()==null) {
			
			food.setIsSpecial("sp");
		}else {
			food.setIsSpecial(null);
		}
		
		return foodRepository.save(food);
		
	}
	
	public Food settingAvailability(Integer foodId) throws Exception {
		
     Food food = findFoodByFoodId(foodId);
		
		if(food.getAvailability()==null) {
			
			food.setAvailability("av");
		}else {
			food.setAvailability(null);
		}
		return foodRepository.save(food);
	}
	
	public Comment createFoodComment(Comment comment, Integer foodId, Integer userId) throws Exception {
		
		
		 User user = userService.findUserById(userId);
//		    RestaurentPost post = restropostService.findRestaurentPostByPostId(postId);
		    
		    Food food = findFoodByFoodId(foodId);
		    

		    comment.setUser(user);
		    comment.setContent(comment.getContent());

		    comment.setCreatedAt(LocalDateTime.now());

		    Comment savedComment = commentRepository.save(comment);
	         
//		   post.getComments().add(savedComment.);
		    
		    food.getComments().add(savedComment);
		    
		    foodRepository.save(food);

		    return savedComment;
	}

//	@Override
//	public List<Food> getFoodsOrderByAvgRatingAndRatingCount() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

//	   private final FoodRepository foodRepository;

//	    @Autowired
//	    public FoodService(FoodRepository foodRepository) {
//	        this.foodRepository = foodRepository;
//	    }

	   public List<Object[]> findRestaurantsAndFoodByAvgRatingDesc() {
	        return foodRepository.findRestaurantsAndFoodByAvgRatingDesc();
	    }
//	    
	

}
