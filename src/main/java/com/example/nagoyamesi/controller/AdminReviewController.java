
	package com.example.nagoyamesi.controller;

	import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nagoyamesi.repository.RoleRepository;
import com.example.nagoyamesi.repository.UserRepository;

	@Controller
	@RequestMapping("/admin/revenue")
	public class AdminReviewController {
		private final UserRepository userRepository;
		private final RoleRepository roleRepository;
		//private final SubscriptionService subscriptionService;

	

		public AdminReviewController(UserRepository userRepository, RoleRepository roleRepository) {
			this.userRepository = userRepository;
			this.roleRepository = roleRepository;
			//this.subscriptionService = subscriptionService;
		}

		//管理者用売上管理ページ遷移用
		
	}


