package com.example.nagoyamesi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyamesi.entity.Category;
import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.form.CategoryRegisterForm;
import com.example.nagoyamesi.form.RestaurantEditForm;
import com.example.nagoyamesi.form.RestaurantRegisterForm;
import com.example.nagoyamesi.repository.CategoryRepository;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.service.CategoryService;
import com.example.nagoyamesi.service.RestaurantService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {
	private final RestaurantRepository restaurantRepository;
	private final RestaurantService restaurantService;
	private final CategoryRepository categoryRepository;
	private final CategoryService categoryService;

	public AdminRestaurantController(RestaurantRepository restaurantRepository, RestaurantService restaurantService,
			CategoryRepository categoryRepository, CategoryService categoryService) {
		this.restaurantRepository = restaurantRepository;
		this.restaurantService = restaurantService;
		this.categoryRepository = categoryRepository;
		this.categoryService = categoryService;

	}

	@GetMapping
	public String index(Model model,
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

		return "admin/restaurants/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Restaurant restaurants = restaurantRepository.getReferenceById(id);

		model.addAttribute("restaurants", restaurants);

		return "admin/restaurants/show";

	}

	//管理者用店舗一覧の店舗新規登録ボタン押下後の処理。
	@GetMapping("/restaurantRegister")
	public String restaurantRegister(Model model) {

		List<Category> categories;
		categories = categoryRepository.findAll();

		model.addAttribute("categories", categories);
		model.addAttribute("restaurantRegisterForm", new RestaurantRegisterForm());
		return "admin/restaurants/register";
	}

	//管理者用店舗一覧のカテゴリー新規登録ボタン押下後の処理。
	@GetMapping("/categoryRegister")
	public String categoryRegister(Model model) {

		model.addAttribute("categoryRegisterForm", new CategoryRegisterForm());
		return "admin/category/register";
	}

	//店舗登録処理実行。実行後は店舗一覧へリダイレクトし、成功メッセージ表示。
	@PostMapping("/restaurantCreate")
	public String restaurantCreate(@ModelAttribute @Validated RestaurantRegisterForm restaurantRegisterForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/restaurants/register";
		}

		restaurantService.create(restaurantRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗を登録しました。");

		return "redirect:/admin/restaurants";
	}

	//カテゴリー登録処理実行。実行後は店舗一覧へリダイレクトし、成功メッセージ表示。
	@PostMapping("/categoryCreate")
	public String categoryCreate(@ModelAttribute @Validated CategoryRegisterForm categoryRegisterForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		String getCategory = categoryRegisterForm.getCategory_name();
		Category categoryCheck = categoryRepository.findByCategory(getCategory);

		if (categoryCheck != null) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "category_name",
					"そのカテゴリーは既に登録されています。");
			bindingResult.addError(fieldError);
		}
		if (bindingResult.hasErrors()) {
			return "admin/category/register";
		}

		categoryService.create(categoryRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "カテゴリーを登録しました。");

		return "redirect:/admin/restaurants";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Restaurant restaurant = restaurantRepository.getReferenceById(id);
		String imageName = restaurant.getImage_name();
		RestaurantEditForm restaurantEditForm = new RestaurantEditForm(restaurant.getId(), restaurant.getCategoryName(),
				restaurant.getName(), null,
				restaurant.getDescription(), restaurant.getLowest_price(), restaurant.getHighest_price(),
				restaurant.getOpening_time(), restaurant.getClosing_time(), restaurant.getRegular_holiday(),
				restaurant.getCapacity(), restaurant.getAddress(), restaurant.getPhone_number());
		List<Category> categories;
		categories = categoryRepository.findAll();

		model.addAttribute("categories", categories);
		model.addAttribute("imageName", imageName);
		model.addAttribute("restaurantEditForm", restaurantEditForm);

		return "admin/restaurants/edit";
	}

	@PostMapping("/{id}/update")
	public String update(@ModelAttribute @Validated RestaurantEditForm houseEditForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/restautants/edit";
		}

		restaurantService.update(houseEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗情報を編集しました。");

		return "redirect:/admin/restaurants";
	}
	
	//管理者ページの店舗一覧で、削除ボタンを押下後の処理を実装
	@PostMapping("/{id}/delete")
	@Transactional
	public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {

		try {
			//外部キー制約を削除
			restaurantService.dropForeignKey();

			restaurantRepository.deleteById(id);
			//削除した外部キー制約を再生成
			restaurantService.createForeignKey();

			// 成功した場合の処理
			redirectAttributes.addFlashAttribute("successMessage", "店舗を削除しました。");

		} catch (Exception e) {
			// 処理に失敗した場合の処理
			if (e.getMessage() != null) {

				redirectAttributes.addFlashAttribute("successMessage", "エラー発生");
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "エラー発生");
			}
			return "redirect:/admin/restaurants";
		}

		redirectAttributes.addFlashAttribute("successMessage", "店舗を削除しました。");

		return "redirect:/admin/restaurants";
	}

}
