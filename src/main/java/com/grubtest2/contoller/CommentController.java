package com.grubtest2.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.grubtest2.Models.Comment;
import com.grubtest2.Models.RestaurentComment;
import com.grubtest2.Models.User;
import com.grubtest2.service.CommentService;
import com.grubtest2.service.RestaurentCommentService;
import com.grubtest2.service.UserService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RestaurentCommentService restroCommentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment(@RequestBody Comment comment , @RequestHeader("Authorization") String jwt, @PathVariable("postId") Integer postId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Comment createdComment = commentService.createComment(comment, postId, user.getId());
		
		return createdComment;
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable("commentId") Integer commentId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Comment likedComment = commentService.likeComment(commentId, user.getId());
		
		return likedComment;
	}
	
	
	@PostMapping("/api/restrocomments/{restroId}")
	public RestaurentComment createRestroComment(@RequestBody RestaurentComment comment , @RequestHeader("Authorization") String jwt, @PathVariable("restroId") Integer restroId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		RestaurentComment createdComment = restroCommentService.createComment(comment, restroId, user.getId());
		
		return createdComment;
	}
	
}
