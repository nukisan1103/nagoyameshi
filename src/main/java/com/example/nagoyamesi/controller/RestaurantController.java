package com.example.nagoyamesi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.form.ReservationInputForm;
import com.example.nagoyamesi.repository.RestaurantRepository;

@Controller
@RequestMapping(value = {"/restaurants"})
public class RestaurantController {
	private final RestaurantRepository restaurantRepository;

	public RestaurantController(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;

	}
	
	//無料会員用店舗一覧and店舗検索ページ
	@GetMapping
	public String generalIndex(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(name = "keyword", required = false) String keyword) {
		Page<Restaurant> restaurants;

		if (keyword != null && !keyword.isEmpty()) {

			restaurants = restaurantRepository.findByNameLike("%" + keyword + "%", pageable);

		} else {

			restaurants = restaurantRepository.findAll(pageable);

		}

		model.addAttribute("restaurants", restaurants);
		model.addAttribute("keyword", keyword);

		return "general/restaurants/index";
	}
	
	//無料会員用店舗詳細ページ
	@GetMapping("/{id}")
	public String generalShow(@PathVariable(name = "id") Integer id, Model model) {
		Restaurant restaurants = restaurantRepository.getReferenceById(id);

		model.addAttribute("restaurants", restaurants);

		return "general/restaurants/show";
	}
	
	//無料会員用店舗一覧and店舗検索ページ
	@GetMapping("/subscriber")
	public String subscriberIndex(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(name = "keyword", required = false) String keyword) {
		Page<Restaurant> restaurants;

		if (keyword != null && !keyword.isEmpty()) {

			restaurants = restaurantRepository.findByNameLike("%" + keyword + "%", pageable);

		} else {

			restaurants = restaurantRepository.findAll(pageable);

		}

		model.addAttribute("restaurants", restaurants);
		model.addAttribute("keyword", keyword);

		return "subscriber/restaurants/index";
	}
	
	//無料会員用店舗詳細ページ
	@GetMapping("/subscriber/{id}")
	public String subscriberShow(@PathVariable(name = "id") Integer id, Model model) {
		Restaurant restaurants = restaurantRepository.getReferenceById(id);

	    model.addAttribute("reservationInputForm", new ReservationInputForm());
		model.addAttribute("restaurants", restaurants);

		return "subscriber/restaurants/show";
	}
	

}
