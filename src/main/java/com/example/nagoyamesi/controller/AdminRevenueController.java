package com.example.nagoyamesi.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nagoyamesi.entity.Role;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.repository.RoleRepository;
import com.example.nagoyamesi.repository.UserRepository;
import com.example.nagoyamesi.security.UserDetailsImpl;

@Controller
@RequestMapping("/admin/revenue")
public class AdminRevenueController {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	//private final SubscriptionService subscriptionService;

	private static final int SUBSCRIPTION_FEE = 330;

	public AdminRevenueController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		//this.subscriptionService = subscriptionService;
	}

	//管理者用売上管理ページ遷移用
	@GetMapping
	public String index(Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {

		Role role = roleRepository.findByName("ROLE_SUBSCRIBER");
		List<User> user = userRepository.findByRole(role);
		Map<String,Long> revenueInfo = new HashMap<>();
		
		long period = 0;
		long totalRevenue = 0;
		for (int i = 0; i < user.size(); i++) {
			long revenue = 0;
			if (user.get(i).getSubscriptionStartDate() != null) {
				LocalDate startDate = user.get(i).getSubscriptionStartDate();
				LocalDate today = LocalDate.now();
				period = ChronoUnit.MONTHS.between(startDate, today);
				revenue = period * SUBSCRIPTION_FEE;
				totalRevenue += revenue;
				revenueInfo.put(user.get(i).getName(), revenue);
			}
		}

		System.out.println(totalRevenue);
		
		 model.addAttribute("totalRevenue", totalRevenue);
		 model.addAttribute("revenueInfo", revenueInfo);

		return "admin/revenue/index";
	}

}
