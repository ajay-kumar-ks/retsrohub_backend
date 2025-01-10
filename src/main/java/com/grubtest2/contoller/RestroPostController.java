package com.grubtest2.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.grubtest2.Models.Comment;
import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.RestaurentComment;
import com.grubtest2.Models.RestaurentPost;
import com.grubtest2.Models.User;
import com.grubtest2.response.ApiResponse;
import com.grubtest2.service.RestaurentCommentService;
import com.grubtest2.service.RestaurentPostService;
import com.grubtest2.service.RestaurentService;
import com.grubtest2.service.UserService;

@RestController
public class RestroPostController {
	
	@Autowired
	RestaurentPostService restroPostService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RestaurentService restroService;
	
	@Autowired
	RestaurentCommentService commentService;
	
	@PostMapping("/api/restroposts")
	public ResponseEntity<RestaurentPost> createRestroPost(@RequestBody  RestaurentPost post,@RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		RestaurentPost createdPost = restroPostService.createNewRestroPost(post, reqUser.getId());
		
		return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/api/restroposts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt) throws Exception{
		
		
		User reqUser = userService.findUserByJwt(jwt);
		String message = restroPostService.deleteRestroPost(postId, reqUser.getId());
		ApiResponse res = new ApiResponse(message,true);
		
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
		
	}
	
	@GetMapping("/api/restroposts/{postId}")
	public ResponseEntity<RestaurentPost>findPostByIdHandler(@PathVariable Integer postId) throws Exception{
		
		RestaurentPost post = restroPostService.findRestaurentPostByPostId(postId);
		
		return new ResponseEntity<RestaurentPost>(post,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/api/restroposts/restaurent/{userId}")
	public ResponseEntity<List<RestaurentPost>>findRestaurentPost(@PathVariable Integer userId){
		
		Restaurent restro = restroService.findRestaurentByUserId(userId);
		
		List<RestaurentPost> posts = restroPostService.findRestroPostByRestroId(restro.getId());
		
		return new ResponseEntity<List<RestaurentPost>>(posts,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/restroposts")
	public ResponseEntity<List<RestaurentPost>> findRestroPost() {
	    List<RestaurentPost> posts = restroPostService.findAllRestroPost();
	    return new ResponseEntity<List<RestaurentPost>>(posts, HttpStatus.ACCEPTED);
	}

	
	
	@PutMapping("/api/restroposts/like/{postId}")
	public ResponseEntity<RestaurentPost>likePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		RestaurentPost post = restroPostService.likeRestroPost(postId, reqUser.getId());
	
		return new ResponseEntity<RestaurentPost>(post,HttpStatus.ACCEPTED);
	}
	
	
	//comment
	
	
	@PostMapping("/api/restrocomments/post/{postId}")
	public RestaurentComment createComment(@RequestBody RestaurentComment comment , @RequestHeader("Authorization") String jwt, @PathVariable("postId") Integer postId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		RestaurentComment createdComment = commentService.createComment(comment, postId, user.getId());
		
		return createdComment;
	}
	
	@PutMapping("/api/restrocomments/like/{commentId}")
	public RestaurentComment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable("commentId") Integer commentId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		RestaurentComment likedComment = commentService.likeComment(commentId, user.getId());
		
		return likedComment;
	}
	
	
	
	

}
