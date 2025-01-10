package com.grubtest2.service;

import java.util.List;

import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.RestaurentPost;
import com.grubtest2.Models.User;

public interface RestaurentPostService {
	
    RestaurentPost createNewRestroPost(RestaurentPost post, Integer userId) throws Exception;
	
	String deleteRestroPost(Integer postId, Integer userId) throws Exception;
	
	List<RestaurentPost> findRestroPostByRestroId(Integer restro);
	
	RestaurentPost findRestaurentPostByPostId(Integer postId) throws Exception;
	
	List<RestaurentPost> findAllRestroPost();
	
	RestaurentPost likeRestroPost(Integer PostId, Integer userId) throws Exception;



}
