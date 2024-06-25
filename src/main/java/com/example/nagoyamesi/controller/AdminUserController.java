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

import com.example.nagoyamesi.entity.Role;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.repository.RoleRepository;
import com.example.nagoyamesi.repository.UserRepository;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public AdminUserController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		Page<User> userPage;

		if (keyword != null && !keyword.isEmpty()) {
			userPage = userRepository.findByNameLikeOrKanaLike("%" + keyword + "%", "%" + keyword + "%", pageable);
		} else {
			userPage = userRepository.findAll(pageable);
		}

		model.addAttribute("userPage", userPage);
		model.addAttribute("keyword", keyword);

		return "admin/users/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		User user = userRepository.getReferenceById(id);

		model.addAttribute("user", user);

		return "admin/users/show";
	}

	@GetMapping("/roleresearch")
	public String roleReserch(@RequestParam(name = "keyword", required = false) String keyword,
			@PageableDefault(page = 0, size = 20, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		
		Page<User> userPage;
		
		Role role = roleRepository.findByName(keyword);
		userPage = userRepository.findByRole(role, pageable);
		

		model.addAttribute("userPage", userPage);

		return "admin/users/index";
	}

}
