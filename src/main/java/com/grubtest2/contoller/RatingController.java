package com.grubtest2.contoller;

import com.grubtest2.Models.Rating;
import com.grubtest2.Models.User;
import com.grubtest2.exception.UserException;
import com.grubtest2.service.RatingService;
import com.grubtest2.service.RestaurentService;
import com.grubtest2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

	@Autowired
     RatingService ratingService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RestaurentService restroService;

  

	@PostMapping("/{foodId}")
	public ResponseEntity<Object> createRating(@RequestBody Rating rating,
	        @RequestHeader("Authorization") String jwt, @PathVariable Integer foodId) {
		System.out.println(rating);
	    try {
	        User reqUser = userService.findUserByJwt(jwt);
	        
	        Rating newRating = ratingService.createNewRating(rating, reqUser.getId(), foodId);
	        
	        if (newRating != null) {
	            return ResponseEntity.status(HttpStatus.CREATED).body("created");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create rating");
	        }
	    } catch (UserException e) {
	        // Handle UserException (e.g., user not found)
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        // Handle other exceptions
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
	    }
	}
	
	@PostMapping("/restro/{restroId}")
	public ResponseEntity<Object> createRestroRating(@RequestBody Rating rating,
	        @RequestHeader("Authorization") String jwt, @PathVariable Integer restroId) {
		System.out.println(rating);
	    try {
	        User reqUser = userService.findUserByJwt(jwt);
	        
	        Rating newRating = restroService.createNewRating(rating, reqUser.getId(), restroId);
	        
	        if (newRating != null) {
	            return ResponseEntity.status(HttpStatus.CREATED).body("created");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create rating");
	        }
	    } catch (UserException e) {
	        // Handle UserException (e.g., user not found)
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        // Handle other exceptions
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
	    }
	}


//
//    @GetMapping("/food/{foodId}")
//    public ResponseEntity<List<Double>> getRatingValuesByFoodId(@PathVariable Integer foodId) {
//        List<Double> ratingValues = ratingService.findRatingValuesByFoodId(foodId);
//        return ResponseEntity.ok(ratingValues);
//    }
//
//    @GetMapping("/{rateId}")
//    public ResponseEntity<Rating> getRatingByRateId(@PathVariable Integer rateId) {
//        Rating rating = ratingService.findRatingByRateId(rateId);
//        if (rating != null) {
//            return ResponseEntity.ok(rating);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/average/{foodId}")
//    public ResponseEntity<Double> getAverageRating(@PathVariable Integer foodId) {
//        Double averageRating = ratingService.findAvarageRating(foodId);
//        if (averageRating != null) {
//            return ResponseEntity.ok(averageRating);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}

