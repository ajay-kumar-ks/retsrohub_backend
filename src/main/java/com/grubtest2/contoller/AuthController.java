package com.grubtest2.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grubtest2.Models.User;
import com.grubtest2.config.JwtProvider;
import com.grubtest2.repository.UserRepository;
import com.grubtest2.request.LoginRequest;
import com.grubtest2.response.AuthResponse;
import com.grubtest2.service.CustomUserDetailsService;
import com.grubtest2.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    @Autowired
	private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CustomUserDetailsService customeUserDetails;
	
	@PostMapping("/users")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if(isExist!=null) {
			throw new Exception("email already use with another account");
		}
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser=userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser, savedUser.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res=new AuthResponse(token, "Register Success");
		
		return res;
	}
	
	@PostMapping("/signin")
	 public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		
		
	   String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res=new AuthResponse(token, "login Success");
		
		return res;
	}

	private Authentication authenticate(String email, String password) {
		
		UserDetails userDetails = customeUserDetails.loadUserByUsername(email);
		
		if(userDetails==null) {
			throw new BadCredentialsException("invalied Username");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not match");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
}
