package com.grubtest2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.User;

public interface RestaurentRepository extends JpaRepository<Restaurent, Integer>{
	
	@Query("select r from Restaurent r where r.restroName LIKE %:query% OR r.city LIKE %:query%")
	public List<Restaurent> searchRestaurent(@Param("query") String query);

	@Query("select r from Restaurent r where r.user.id = :userId")
	public Restaurent findRestaurentByUserId(Integer userId);

}
