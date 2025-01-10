package com.grubtest2.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @OneToOne
    private User user;
    
    private String restroName;
    
    private String city;
    
    private String location;
    
    private String discription;

    private String adress;

    private List<Integer> followers = new ArrayList<>();
    
    @OneToMany
	private List<Rating> ratings=new ArrayList<>();
    
    @OneToMany
    private List<RestaurentComment> restroComments = new ArrayList<>();
    
    // Explicitly initialize the 'adress' and 'discription' fields in the constructor
    public Restaurent(Integer id, User user, String restroName, String city, String location, String discription, String adress) {
        this.id = id;
        this.user = user;
        this.restroName = restroName;
        this.city = city;
        this.location = location;
        this.discription = discription;
        this.adress = adress;
    }
    
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    
}
