package com.grubtest2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grubtest2.Models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	
	
	
}
