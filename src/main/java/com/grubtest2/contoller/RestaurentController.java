package com.grubtest2.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grubtest2.Models.Restaurent;
import com.grubtest2.Models.User;
import com.grubtest2.exception.UserException;
import com.grubtest2.repository.RestaurentRepository;
import com.grubtest2.repository.UserRepository;
import com.grubtest2.service.RestaurentService;
import com.grubtest2.service.UserService;

@RestController
public class RestaurentController {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RestaurentRepository restroRepository;
	
	@Autowired
	RestaurentService restroService;
	
	
	@PostMapping("/api/restaurents")
	public Restaurent createRestaurent(@RequestBody Restaurent restro,@RequestHeader("Authorization")String jwt) throws UserException{
		
		User reqUser = userService.findUserByJwt(jwt);
		Restaurent createdRestaurent = restroService.registerRestaurent(restro, reqUser.getId());
		
		 

		
		return createdRestaurent;
	}
	
	
	@GetMapping("/api/restaurents/{restroId}")
	public Restaurent getRestaurentByid(@PathVariable("restroId")Integer id) throws Exception {
		
		Restaurent restro = restroService.findRestaurentById(id);
		
		return restro;
		
	}
	
	
	
	@GetMapping("/api/user/restaurents")
	public Restaurent GetRestaurentByUser(@RequestHeader("Authorization")String jwt ) throws UserException{
		
		User reqUser=userService.findUserByJwt(jwt);
		
		Restaurent reqRestro=restroService.findRestaurentByUserId(reqUser.getId());
		
		
		
		return reqRestro;
		
	}
	
	
	
	
	@PutMapping("/api/restaurents")
	public Restaurent updateRestaurent(@RequestHeader("Authorization")String jwt ,@RequestBody Restaurent restaurent) throws UserException{
		
		User reqUser=userService.findUserByJwt(jwt);
		
		Restaurent reqRestro=restroService.findRestaurentByUserId(reqUser.getId());
		
		Restaurent updatedRestaurent = restroService.upadateRestaurent(restaurent, reqRestro.getId());
		
		return updatedRestaurent;
		
	}
	
	@GetMapping("/api/restaurents")
	public List<Restaurent> getRestaurents() {
		
		List<Restaurent> restro=restroRepository.findAll();
		
		
		return restro;
	}
	
	@GetMapping("/restaurents/search")
	public List<Restaurent> serachRestaurent(@RequestParam("query") String query) {
		
		List<Restaurent> restro = restroService.searchRestaurent(query);
		
		return restro;
	}
	
	
}
