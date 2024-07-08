package com.example.nagoyamesi.service;


import org.springframework.stereotype.Service;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.Review;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.ReviewForm;
import com.example.nagoyamesi.repository.ReviewRepository;


@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
		
	}
	
	public void create(User user,Restaurant restaurants,ReviewForm reviewForm) {
		
		Review review = new Review();
		
		
		review.setUser(user);
		review.setRestaurant(restaurants);
		review.setScore(reviewForm.getScore());
		review.setSentense(reviewForm.getSentense());

		reviewRepository.save(review);
	}
	  
}
