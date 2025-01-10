package com.grubtest2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grubtest2.Models.Post;
import com.grubtest2.Models.RestaurentPost;

public interface RestaurentPostRepository extends JpaRepository<RestaurentPost, Integer>{
	
	@Query("select rp from RestaurentPost rp where rp.restaurent.id = :restaurentId")
	List<RestaurentPost> findPostByRestroId(@Param("restaurentId") Integer restaurentId);

}
