package com.grubtest2.service;

import com.grubtest2.Models.Comment;

public interface CommentService {

	
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
	
	public Comment findCommentById(Integer commentId) throws Exception;
	
	public Comment likeComment(Integer CommentId, Integer userId) throws Exception;
}
