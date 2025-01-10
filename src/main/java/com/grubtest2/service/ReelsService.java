package com.grubtest2.service;

import java.util.List;

import com.grubtest2.Models.Reels;
import com.grubtest2.Models.User;

public interface ReelsService {

	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReel(Integer userId) throws Exception;
	
	
}
