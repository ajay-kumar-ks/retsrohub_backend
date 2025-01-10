package com.grubtest2.service;

import java.util.List;

import com.grubtest2.Models.User;
import com.grubtest2.exception.UserException;

public interface UserService {

	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws UserException;
	
	public User findUserBYEmail(String email);
	
	public User followUser(Integer uderId1, Integer userId2) throws UserException;
	
	public User upadateUser(User user, Integer userId) throws UserException;
	
	public List<User> searchUser(String query); 
	
	public User findUserByJwt(String jwt);
}
