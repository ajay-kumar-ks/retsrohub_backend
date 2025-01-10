package com.grubtest2.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grubtest2.Models.Comment;
import com.grubtest2.Models.Food;
import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.User;
import com.grubtest2.response.ApiResponse;
import com.grubtest2.service.CommentService;
import com.grubtest2.service.FoodService;
import com.grubtest2.service.RestaurentCommentService;
import com.grubtest2.service.RestaurentPostService;
import com.grubtest2.service.RestaurentService;
import com.grubtest2.service.UserService;

@RestController
public class FoodController {
	

	@Autowired
	RestaurentPostService restroPostService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RestaurentService restroService;
	
	@Autowired
	RestaurentCommentService commentRestroService;
	
	@Autowired
	FoodService foodService;
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/api/foods")
	public ResponseEntity<Food> createFood(@RequestBody  Food food,@RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Food createdFood = foodService.createNewFood(food, reqUser.getId());
		
		return new ResponseEntity<>(createdFood, HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/api/foods/{foodId}")
	public ResponseEntity<ApiResponse> deleteFood(@PathVariable Integer foodId,@RequestHeader("Authorization")String jwt) throws Exception{
		
		
		User reqUser = userService.findUserByJwt(jwt);
		String message = foodService.deleteFood(foodId, reqUser.getId());
		ApiResponse res = new ApiResponse(message,true);
		
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
		
	}
	
	@GetMapping("/api/foods/{foodId}")
	public ResponseEntity<Food>findFoodByIdHandler(@PathVariable Integer foodId) throws Exception{
		
		Food food = foodService.findFoodByFoodId(foodId);
		
		return new ResponseEntity<Food>(food,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/api/foods/restaurent/{userId}")
	public ResponseEntity<List<Food>>findRestaurentFood(@PathVariable Integer userId){
		
		System.out.println(userId);
		
		Restaurent restro = restroService.findRestaurentByUserId(userId);
		
		List<Food> foods = foodService.findFoodByRestroId(restro.getId());
		
		return new ResponseEntity<List<Food>>(foods,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/foods")
	public ResponseEntity<List<Food>> findFoods() {
	    List<Food> foods = foodService.findAllFood();
	    return new ResponseEntity<List<Food>>(foods, HttpStatus.ACCEPTED);
	}

	
	
	@PutMapping("/api/foods/like/{foodId}")
	public ResponseEntity<Food>likeFoodHandler(@PathVariable Integer foodId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		Food food = foodService.likeFood(foodId, reqUser.getId());
	
		return new ResponseEntity<Food>(food,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/api/foods/avail/{foodId}")
	public ResponseEntity<Food>settingFoodAvailability(@PathVariable Integer foodId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		Food food = foodService.settingAvailability(foodId);
		
		return new ResponseEntity<Food>(food,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/foods/specials/{foodId}")
	public ResponseEntity<Food>settingFoodSpecial(@PathVariable Integer foodId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		Food food = foodService.settingSpecialFood(foodId);
		
		return new ResponseEntity<Food>(food,HttpStatus.ACCEPTED);
	}
	
	
	
	
	//comment
	

      @PostMapping("/api/foodcomments/{foodId}")
	public Comment createComment(@RequestBody Comment comment , @RequestHeader("Authorization") String jwt, @PathVariable("foodId") Integer foodId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Comment createdComment = foodService.createFoodComment(comment, foodId, user.getId());
		
		return createdComment;
	}
	
//  	@GetMapping("/api/trendfoods/")
//  	public List<Food>getFoodsOrderByAvgRatingAndRatingCount(){
//  		
//  		
//  		
//  		return foodService.getFoodsOrderByAvgRatingAndRatingCount();
//  	} 
  	
    @GetMapping("/food/trendfoods")
    public List<Object[]> getFoodsOrderedByRating() {
        return foodService.findRestaurantsAndFoodByAvgRatingDesc();
    }
      
      
//	@PutMapping("/api/restrocomments/like/{commentId}")
//	public RestaurentComment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable("commentId") Integer commentId) throws Exception {
//		
//		User user = userService.findUserByJwt(jwt);
//		
//		RestaurentComment likedComment = commentService.likeComment(commentId, user.getId());
//		
//		return likedComment;
//	}

}
