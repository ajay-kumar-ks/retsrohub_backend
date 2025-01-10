package com.grubtest2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grubtest2.Models.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
//	@Query("select p from post p where p.user.id=:userId")
//	List<Post>findPostByUserId(Integer userId);
//			
	@Query("select p from Post p where p.user.id = :userId")
	List<Post> findPostByUserId(@Param("userId") Integer userId);

   
}
