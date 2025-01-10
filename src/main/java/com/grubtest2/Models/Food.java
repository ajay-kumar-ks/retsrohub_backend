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
import jakarta.persistence.OneToOne;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;

    private String image;

    private String name;

    private String availability;

    private Integer price;

    @ManyToOne
    private Restaurent restaurent;

    private LocalDateTime createdAt;

    private String isSpecial;
    
    @OneToMany
	private List<Rating> ratings=new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    List<User> liked = new ArrayList<>();

    @OneToMany
    private List<Rating> rating = new ArrayList<>();

    public Food() {
    }

	public Food(Integer id, String caption, String image, String name, String availability, Integer price,
			Restaurent restaurent, LocalDateTime createdAt, String isSpecial, List<Rating> ratings,
			List<Comment> comments, List<User> liked, List<Rating> rating) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.name = name;
		this.availability = availability;
		this.price = price;
		this.restaurent = restaurent;
		this.createdAt = createdAt;
		this.isSpecial = isSpecial;
		this.ratings = ratings;
		this.comments = comments;
		this.liked = liked;
		this.rating = rating;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
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

	public String getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
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

	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}
    
    

//	 public void setRating(User user, double value) {
//	        if (!hasUserRated(user)) {
//	            if (this.rating == null) {
//	                this.rating = new Rating();
//	                this.rating.setFood(this);
//	            }
//
//	            this.rating.setUser(user);
//	            this.rating.setValue(value);
//	        }
//	    }
//	 
//	 public boolean hasUserRated(User user) {
//	        return this.rating != null && this.rating.getUser().equals(user);
//	    }

	
	

}
