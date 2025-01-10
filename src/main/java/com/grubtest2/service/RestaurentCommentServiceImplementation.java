package com.grubtest2.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grubtest2.Models.Comment;
import com.grubtest2.Models.Post;
import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.RestaurentComment;
import com.grubtest2.Models.RestaurentPost;
import com.grubtest2.Models.User;
import com.grubtest2.repository.RestaurentPostRepository;
import com.grubtest2.repository.RestaurentRepository;
import com.grubtest2.repository.RestroCommentRepository;

@Service
public class RestaurentCommentServiceImplementation implements RestaurentCommentService{
	
	@Autowired
	RestaurentPostService restropostService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RestroCommentRepository restroCommentRepository;
	
	@Autowired
	RestaurentPostRepository restroPostRepository;
	
	@Autowired
	RestaurentRepository restaurentRepository;
	
	@Autowired
	RestaurentService restaurentService;


	@Override
	public RestaurentComment createComment(RestaurentComment comment, Integer restroId, Integer userId) throws Exception {
	    User user = userService.findUserById(userId);
//	    RestaurentPost post = restropostService.findRestaurentPostByPostId(postId);
	    
	    Restaurent restro = restaurentService.findRestaurentById(restroId);
	    

	    comment.setUser(user);
	    comment.setContent(comment.getContent()); // Corrected this line
	    comment.setCreatedAt(LocalDateTime.now());

	    RestaurentComment savedComment = restroCommentRepository.save(comment);
         
//	   post.getComments().add(savedComment.);
	    
	    restro.getRestroComments().add(savedComment);
	    
	  
	    restaurentRepository.save(restro);

	    return savedComment;
	}



	@Override
	public RestaurentComment findCommentById(Integer commentId) throws Exception {
	

		Optional<RestaurentComment> opt=restroCommentRepository.findById(commentId);
		
		if(opt.isEmpty()) {
			throw new Exception("comment not exist");
		}
		return opt.get();
	}

	@Override
	public RestaurentComment likeComment(Integer CommentId, Integer userId) throws Exception {
		
		
		RestaurentComment comment = findCommentById(CommentId);
		User user = userService.findUserById(userId);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}
		else comment.getLiked().remove(user);
		
		return restroCommentRepository.save(comment);
	}
	

}
