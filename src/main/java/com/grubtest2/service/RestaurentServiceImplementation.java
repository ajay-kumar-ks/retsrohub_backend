package com.grubtest2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grubtest2.Models.Food;
import com.grubtest2.Models.Rating;
import com.grubtest2.Models.RatingRepository;
import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.User;
import com.grubtest2.exception.UserException;
import com.grubtest2.repository.FoodRepository;
import com.grubtest2.repository.RestaurentRepository;
import com.grubtest2.repository.UserRepository;

@Service
public class RestaurentServiceImplementation implements RestaurentService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestaurentRepository restroRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Restaurent registerRestaurent(Restaurent restaurent, Integer userId) throws UserException {
		
		User user=userService.findUserById(userId);
		
		Restaurent newRestro = new Restaurent();
		newRestro.setRestroName(restaurent.getRestroName());
		newRestro.setCity(restaurent.getCity());
		newRestro.setLocation(restaurent.getLocation());
		newRestro.setId(restaurent.getId());
		newRestro.setUser(user);
		newRestro.setAdress(restaurent.getAdress());
		newRestro.setDiscription(restaurent.getDiscription());
		
		Restaurent savedRestro=restroRepository.save(newRestro);
		
		return savedRestro;
		
	}

	@Override
	public Restaurent findRestaurentById(Integer restaurentId) throws Exception {
         Optional<Restaurent> restro=restroRepository.findById(restaurentId);
		
		
		if(restro.isEmpty())
		{
			throw new Exception("restaurent not found "+ restaurentId);
		}
		return restro.get();
		
	}

	@Override
	public Restaurent findRestaurentByUserId(Integer userId) {
		return restroRepository.findRestaurentByUserId(userId);
		
	}

	@Override
	public Restaurent upadateRestaurent(Restaurent restaurent, Integer restaurentId) throws UserException {
		
		java.util.Optional<Restaurent> restro=restroRepository.findById(restaurentId);
		
		if(restro.isEmpty()) {
			throw new UserException("restaurent doesnt exist with restro id "+restaurentId);
		}
		
		Restaurent olsrestro=restro.get();
		
		if(restaurent.getRestroName()!=null) {
			olsrestro.setRestroName(restaurent.getRestroName());
		}
		if(restaurent.getCity()!=null) {
			olsrestro.setCity(restaurent.getCity());
		}
		if(restaurent.getLocation()!=null) {
			olsrestro.setLocation(restaurent.getLocation());
		}
		
	
		Restaurent updatedRestro=restroRepository.save(olsrestro);

		
	
	
		return updatedRestro;
	}

	@Override
	public List<Restaurent> searchRestaurent(String query) {
		return restroRepository.searchRestaurent(query);
	}
	
	
	
	@Override
	public Rating createNewRating(Rating rating, Integer userId ,Integer restroId) throws Exception {
		
		
		 User user = userService.findUserById(userId);
	        user.setId(userId);
	        
	        Restaurent restro = findRestaurentById(restroId);
	        restro.setId(restroId);
	       
	        
	        rating.setUser(user);
	        rating.setValue(rating.getValue());
	        
	        
	        

			Rating savedRating=ratingRepository.save(rating);
			
			restro.getRatings().add(savedRating);
		
			restroRepository.save(restro);
			
			return savedRating;
		
	}
	

	

}
