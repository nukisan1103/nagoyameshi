package com.example.nagoyamesi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.Review;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.ReviewForm;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.repository.ReviewRepository;
import com.example.nagoyamesi.repository.UserRepository;
import com.example.nagoyamesi.security.UserDetailsImpl;
import com.example.nagoyamesi.service.ReviewService;

@Controller
@RequestMapping("/admin")
public class ReviewController {
	
 private final ReviewRepository reviewRepository;      
 private final ReviewService reviewservice;
 private final UserRepository userRepository;
 private final RestaurantRepository restaurantRepository;

     
     public ReviewController(ReviewRepository reviewRepository,ReviewService reviewservice,UserRepository userRepository
    		 ,RestaurantRepository restaurantRepository) {        
         this.reviewRepository = reviewRepository;        
         this.reviewservice = reviewservice;
         this.userRepository = userRepository;
         this.restaurantRepository = restaurantRepository;
        
     }    
 
     @GetMapping("/review")
     public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
         User user = userDetailsImpl.getUser();
         Page<Review> reviewPage = reviewRepository.findByUserOrderByCreatedAtDesc(user, pageable);
         
         model.addAttribute("reviewPage", reviewPage);         
         
         return "review/index";
     }
     
     
     @GetMapping("/{id}/review")
     public String postReview(@PathVariable(name = "id") Integer id,Model model) {
    	 
    	 Restaurant restaurants = restaurantRepository.getReferenceById(id);
    	 model.addAttribute("restaurants",restaurants);
    	 model.addAttribute("reviewForm", new ReviewForm());
         return "review/post";
    	 
     }
     @PostMapping("/{id}/review/create")
     public String reviewCreate(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    		 @ModelAttribute @Validated ReviewForm reviewForm,RedirectAttributes redirectAttributes,BindingResult bindingResult
    		 ,@PathVariable(name = "id") Integer id) {
    	     	 
    	 if (bindingResult.hasErrors()) {
             return "admin/restautants/index";
         }
    	 
    	 User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId()); 
    	 Restaurant restaurants = restaurantRepository.getReferenceById(id);
    	 
    	 reviewservice.create(user,restaurants,reviewForm);
    	 redirectAttributes.addFlashAttribute("successMessage", "レビューを登録しました。");   
    	 
    	 return "redirect:/admin/restaurants";
     }

}
