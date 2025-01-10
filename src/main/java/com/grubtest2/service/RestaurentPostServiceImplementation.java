package com.grubtest2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.RestaurentPost;
import com.grubtest2.Models.User;
import com.grubtest2.repository.RestaurentPostRepository;
import com.grubtest2.repository.RestaurentRepository;

@Service
public class RestaurentPostServiceImplementation implements RestaurentPostService{
	
	@Autowired
	RestaurentPostRepository restroPostRepository;
	
	@Autowired
	RestaurentService restroService;
	
	@Autowired
	RestaurentRepository restroRepository;
	
	@Autowired
	UserService userService;

	@Override
	public RestaurentPost createNewRestroPost(RestaurentPost post, Integer userId) throws Exception {
		
		
        Restaurent restroId = restroService.findRestaurentByUserId(userId);
        
		
		RestaurentPost newRestroPost=new RestaurentPost();
		newRestroPost.setCaption(post.getCaption());
		newRestroPost.setImage(post.getImage());
		newRestroPost.setCreatedAt(LocalDateTime.now());
		newRestroPost.setRestaurent(restroId);
		
		return restroPostRepository.save(newRestroPost);
	}

	@Override
	public String deleteRestroPost(Integer postId, Integer userId) throws Exception {
		
		RestaurentPost restroPost = findRestaurentPostByPostId(postId);
		Restaurent restro = restroService.findRestaurentByUserId(userId);
		
		if(restroPost.getId()!=restro.getId()) {
			
			throw new Exception("you cant delete others post");
		}
		
		restroPostRepository.delete(restroPost);
		return "post is deleted";
	}

	@Override
	public List<RestaurentPost> findRestroPostByRestroId(Integer RestaurentId) {
		return restroPostRepository.findPostByRestroId(RestaurentId);
	}
	

	@Override
	public RestaurentPost findRestaurentPostByPostId(Integer postId) throws Exception {
		
		Optional<RestaurentPost> opt=restroPostRepository.findById(postId);
		if(opt.isEmpty())
		{
			throw new Exception("post not found "+ postId);
		}
		return opt.get();
		
	}

	@Override
	public List<RestaurentPost> findAllRestroPost() {
	
		return restroPostRepository.findAll();
	}

	@Override
	public RestaurentPost likeRestroPost(Integer PostId, Integer userId) throws Exception {
		
		RestaurentPost post = findRestaurentPostByPostId(PostId);
		User user = userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			
			post.getLiked().remove(user);
		}else {
			
			post.getLiked().add(user);
		}
		
		
		
		return restroPostRepository.save(post);
	}


	

}
