package com.grubtest2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grubtest2.Models.User;
import com.grubtest2.config.JwtProvider;
import com.grubtest2.exception.UserException;
import com.grubtest2.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		
		User savedUser=userRepository.save(newUser);
		
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
java.util.Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("user not exist with userid "+userId);
	
	}

	@Override
	public User findUserBYEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws UserException {
		 
		User reqUser = findUserById(reqUserId);
		
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		return reqUser;
	}

	@Override
	public User upadateUser(User user , Integer userId) throws UserException {
      java.util.Optional<User> user1=userRepository.findById(userId);
		
		if(user1.isEmpty()) {
			throw new UserException("user not exist with userid "+userId);
		}
		
		User oldUser=user1.get();
		
		if(user.getFirstname()!=null) {
			oldUser.setFirstname(user.getFirstname());
		}
		if(user.getLastname()!=null) {
			oldUser.setLastname(user.getLastname());
		}
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender()!=null)
		{
			oldUser.setGender(user.getGender());
		}
		
		User updatedUser=userRepository.save(oldUser);

		
	
	
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		
		User user=userRepository.findByEmail(email);
		return user;
	}
	
	

}
