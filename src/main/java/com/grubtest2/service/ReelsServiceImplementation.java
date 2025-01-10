package com.grubtest2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grubtest2.Models.Reels;
import com.grubtest2.Models.User;
import com.grubtest2.repository.ReelsRepository;

@Service
@Transactional
public class ReelsServiceImplementation implements ReelsService{

	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Reels createReel(Reels reel, User user) {
		
//		Reels createdReel=new Reels();
//		
//		
//		createdReel.setTitle(reel.getTitle());
//		createdReel.setUser(user);
//		createdReel.setVideo(reel.getVideo());
//		
//		return reelsRepository.save(createdReel);
		
		
		try {
	        Reels createdReel = new Reels();
	        createdReel.setTitle(reel.getTitle());
	        createdReel.setUser(user);
	        createdReel.setVideo(reel.getVideo());
	        return reelsRepository.save(createdReel);
	    } catch (Exception e) {
	        // Handle the exception or log it
	        throw new RuntimeException("Error creating reel", e);
	    }
	}

	@Override
	public List<Reels> findAllReels() {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReel(Integer userId) throws Exception {
		
		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
