package com.example.nagoyamesi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.Review;
import com.example.nagoyamesi.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	 public Page<Review> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
	 public Page<Review> findByRestaurant(Restaurant restaurant,Pageable pageable);
	 
	public Review deleteByRestaurant(Restaurant restaurant);
	
	
	public List<Review> findTop6AllByOrderByScoreDesc();
	

	}

