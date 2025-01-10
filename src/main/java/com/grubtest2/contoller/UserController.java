package com.grubtest2.contoller;

import org.springframework.web.bind.annotation.RestController;

import com.grubtest2.Models.User;
import com.grubtest2.exception.UserException;
import com.grubtest2.repository.UserRepository;
import com.grubtest2.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	

	@GetMapping("/api/users/{userId}")
	public User getUserByid(@PathVariable("userId")Integer id) throws UserException{
		
		User user = userService.findUserById(id);
		
		return user;
		
	}
	
	
	

	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization")String jwt ,@RequestBody User user) throws UserException{
		
		User reqUser=userService.findUserByJwt(jwt);
		
		User updatedUser = userService.upadateUser(user, reqUser.getId());
		
		return updatedUser;
		
	}
	
	@DeleteMapping("/api/users/{userId}")
	public String deleteUser(@PathVariable("userId")Integer userId) throws UserException{
		
	java.util.Optional<User> user=userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new UserException("user not exist with userid "+userId);
		}
		
		userRepository.delete(user.get());
		
		return "user deleted succesfully with id  " + userId;
		
	}
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
		List<User> users=userRepository.findAll();
		
		
		return users;
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User FollowUserHandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer userId2) throws UserException {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User user = userService.followUser(reqUser.getId(), userId2);
		
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> serachUser(@RequestParam("query") String query) {
		
		List<User> users = userService.searchUser(query);
		
		return users;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		
		user.setPassword(null);
		
		return user;
		
	}
} 
