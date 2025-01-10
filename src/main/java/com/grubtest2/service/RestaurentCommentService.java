package com.grubtest2.service;


import com.grubtest2.Models.Comment;
import com.grubtest2.Models.RestaurentComment;

public interface RestaurentCommentService {
	
//	public RestaurentComment createComment(Comment comment, Integer restroId, Integer userId) throws Exception;
	
	public RestaurentComment findCommentById(Integer commentId) throws Exception;
	
	public RestaurentComment likeComment(Integer CommentId, Integer userId) throws Exception;

	RestaurentComment createComment(RestaurentComment comment, Integer restroId, Integer userId) throws Exception;

}
