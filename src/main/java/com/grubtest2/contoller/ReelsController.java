package com.grubtest2.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.grubtest2.Models.Reels;
import com.grubtest2.Models.User;
import com.grubtest2.service.ReelsService;
import com.grubtest2.service.UserService;

@RestController
public class ReelsController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReelsService reelsService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel ,@RequestHeader("Authorization") String jwt) {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Reels createdReels = reelsService.createReel(reel, reqUser);
		
		return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		
		List<Reels> reels = reelsService.findAllReels();
		
		return reels;
	}
	
	
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
		
		
		List<Reels> reels = reelsService.findUsersReel(userId);
		
		return reels;
	}
	
	
}
