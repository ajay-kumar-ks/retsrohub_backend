package com.grubtest2.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grubtest2.Models.Food;


public interface FoodRepository extends JpaRepository<Food, Integer>{
	
	@Query("select f from Food f where f.restaurent.id = :restaurentId")
	List<Food> findFoodByRestroId(@Param("restaurentId") Integer restaurentId);
	
	   @Query("SELECT f, AVG(r.value) as avgRating " +
	           "FROM Food f " +
	           "LEFT JOIN f.rating r " +
	           "GROUP BY f " +
	           "ORDER BY avgRating DESC")
	    List<Object[]> findRestaurantsAndFoodByAvgRatingDesc();
}
