package com.example.nagoyamesi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyamesi.entity.Category;
import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.Review;
import com.example.nagoyamesi.repository.CategoryRepository;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.repository.ReviewRepository;

@Controller
public class HomeController {
	
	 private final ReviewRepository reviewRepository;      
	 private final RestaurantRepository restaurantRepository;
	 private final CategoryRepository categoryRepository;

	     
	     public HomeController(ReviewRepository reviewRepository ,RestaurantRepository restaurantRepository,
	    		 CategoryRepository categoryRepository) {        
	    	 
	         this.reviewRepository = reviewRepository;        	 
	         this.restaurantRepository = restaurantRepository;
	         this.categoryRepository = categoryRepository;	        
	     }    

	
	//トップページ遷移用
    @GetMapping("/")
    public String index(Model model) {
    
//評価の高い順に店舗をソートし、上位六位までのデータをビューに送信
    	List<Review> reviewPage = reviewRepository.findTop6AllByOrderByScoreDesc();
       	
    	model.addAttribute("topReviews", reviewPage);
 

//新着順に店舗をソートし、上位六位までのデータをビューに送信
    	List<Restaurant> restaurants = restaurantRepository.findTop6ByOrderByCreatedAtDesc();
    	
    	model.addAttribute("restaurants", restaurants);
    	
//カテゴリーを全権取得
    	List<Category> categories = categoryRepository.findAll();
    	
    	model.addAttribute("categories", categories);
    	
        return "index";
    }
    
    @GetMapping("/top/{id}")
    public String topSearch(Model model,@PathVariable(name="id") String keyword) {
    	
    	Restaurant restaurants = restaurantRepository.findByName(keyword);
    	model.addAttribute("restaurants",restaurants);
    	
		return "top/show";
    	
    }
    
    @GetMapping("/top/search")
    public String topWordSearch(Model model,@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC)
    Pageable pageable,@RequestParam(name = "keyword", required = false) String keyword) {
    	Page<Restaurant> restaurants;
    	restaurants = restaurantRepository.findByNameLike("%" + keyword + "%", pageable);
    	if(restaurants.getTotalPages()==0) {
    		model.addAttribute("errorMessage", "検索結果はありません。");
    	}else {
    	
    	model.addAttribute("restaurants",restaurants);
    	}
    	
		return "top/search";
    	
    }
    
    @GetMapping("/top/category/{id}")
    public String topCategorySearch(Model model,@PathVariable(name="id") String keyword) {
   
    	List<Restaurant> restaurants = restaurantRepository.findByCategoryName(keyword);
    	model.addAttribute("restaurants",restaurants);
    	
    	return "top/search";
    	
    }
}
    
   
    
    

