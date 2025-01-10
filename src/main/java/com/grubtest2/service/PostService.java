package com.grubtest2.service;

import java.util.List;

import com.grubtest2.Models.Post;

public interface PostService {

	
	Post createNewPost(Post post, Integer userId) throws Exception;
	
	String deletePost(Integer postId, Integer userId) throws Exception;
	
	List<Post> findPostByUserId(Integer userId);
	
	Post findPostByPostId(Integer postId) throws Exception;
	
	List<Post> findAllPost();
	
	Post savedPost(Integer postId, Integer userId) throws Exception;
	
	Post likePost(Integer PostId, Integer userId) throws Exception;
	
	
}
