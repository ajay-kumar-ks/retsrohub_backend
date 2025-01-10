package com.grubtest2.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class RestaurentPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String caption;
	
	private String image;
	
	@ManyToOne
	private Restaurent restaurent;
	
	private LocalDateTime createdAt;
	
	@OneToMany
	private List<Comment> comments=new ArrayList<>();
	
	@ManyToMany
	List<User> liked = new ArrayList<>();
	
	public RestaurentPost() {
		
	}

	public RestaurentPost(Integer id, String caption, String image, Restaurent restaurent, LocalDateTime createdAt,
			List<Comment> comments, List<User> liked) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.restaurent = restaurent;
		this.createdAt = createdAt;
		this.comments = comments;
		this.liked = liked;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Restaurent getRestaurent() {
		return restaurent;
	}

	public void setRestaurent(Restaurent restaurent) {
		this.restaurent = restaurent;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Comment> getComments() {
		return comments;
	}
	

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	
	
	
	
	
	

	public List<User> getLiked() {
		return liked;
	}

	public void setLiked(List<User> liked) {
		this.liked = liked;
	}
	
	
	

}
