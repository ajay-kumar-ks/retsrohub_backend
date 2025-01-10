package com.grubtest2.Models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
	
//	@Query("select r from Rating r where r.food.id = :foodId")
//	List<Rating> findRatingByFoodId(@Param("foodId") Integer foodId);
	
//	 @Query("SELECT r FROM Rating r WHERE r.user.id = :userId AND r.food.id = :foodId")
//	    Rating findByUserAndFood(@Param("userId") Integer userId, @Param("foodId") Integer foodId);

//	    @Query("SELECT AVG(r.value) FROM Rating r WHERE r.food.id = :foodId")
//	    Double calculateAverageRatingForFood(@Param("foodId") Integer foodId);

}




