package com.example.nagoyamesi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.form.RestaurantEditForm;
import com.example.nagoyamesi.form.RestaurantRegisterForm;
import com.example.nagoyamesi.repository.RestaurantRepository;

@Service
public class RestaurantService {
	private final RestaurantRepository restaurantRepository;

	public RestaurantService(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	@Transactional
	public void create(RestaurantRegisterForm restaurantRegisterForm) {
		Restaurant restaurant = new Restaurant();
		MultipartFile imageFile = restaurantRegisterForm.getImageFile();

		if (!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			restaurant.setImage_name(hashedImageName);
		}
		restaurant.setCategoryName(restaurantRegisterForm.getCategory_name());
		restaurant.setName(restaurantRegisterForm.getName());
		restaurant.setDescription(restaurantRegisterForm.getRestaurants_description());
		restaurant.setLowest_price(restaurantRegisterForm.getLowest_price());
		restaurant.setHighest_price(restaurantRegisterForm.getHighest_price());
		restaurant.setOpening_time(restaurantRegisterForm.getOpening_time());
		restaurant.setClosing_time(restaurantRegisterForm.getClosing_time());
		restaurant.setRegular_holiday(restaurantRegisterForm.getRegular_holiday());
		restaurant.setCapacity(restaurantRegisterForm.getCapacity());
		restaurant.setAddress(restaurantRegisterForm.getAddress());
		restaurant.setPhone_number(restaurantRegisterForm.getPhone_number());

		restaurantRepository.save(restaurant);
	}

	@Transactional
	public void update(RestaurantEditForm restaurantEditForm) {
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantEditForm.getId());
		MultipartFile imageFile = restaurantEditForm.getImageFile();

		if (!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			restaurant.setImage_name(hashedImageName);
		}

		restaurant.setName(restaurantEditForm.getName());
		restaurant.setCategoryName(restaurantEditForm.getCategory_name());
		restaurant.setDescription(restaurantEditForm.getRestaurants_description());
		restaurant.setLowest_price(restaurantEditForm.getLowest_price());
		restaurant.setHighest_price(restaurantEditForm.getHighest_price());
		restaurant.setOpening_time(restaurantEditForm.getOpening_time());
		restaurant.setClosing_time(restaurantEditForm.getClosing_time());
		restaurant.setRegular_holiday(restaurantEditForm.getRegular_holiday());
		restaurant.setCapacity(restaurantEditForm.getCapacity());
		restaurant.setAddress(restaurantEditForm.getAddress());
		restaurant.setPhone_number(restaurantEditForm.getPhone_number());

		restaurantRepository.save(restaurant);
	}

	// UUIDを使って生成したファイル名を返す
	public String generateNewFileName(String fileName) {
		String[] fileNames = fileName.split("\\.");
		for (int i = 0; i < fileNames.length - 1; i++) {
			fileNames[i] = UUID.randomUUID().toString();
		}
		String hashedFileName = String.join(".", fileNames);
		return hashedFileName;
	}

	// 画像ファイルを指定したファイルにコピーする
	public void copyImageFile(MultipartFile imageFile, Path filePath) {
		try {
			Files.copy(imageFile.getInputStream(), filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//店舗削除機能実装につき一旦外部キー制約削除
	public void dropForeignKey() {
		String reviewsql = "ALTER TABLE reviews DROP FOREIGN KEY reviews_ibfk_2";
		String reservationsql = "ALTER TABLE reservations DROP FOREIGN KEY reservations_ibfk_1";


		jdbcTemplate.execute(reviewsql);
		jdbcTemplate.execute(reservationsql);
		
		}
	
	//店舗削除成功後、再度外部キー制約作成
	public void createForeignKey() {
		String reviewsql = " ALTER TABLE reviews ADD CONSTRAINT reviews_ibfk_2\n"
				+ "FOREIGN KEY (restaurant_id) REFERENCES restaurants (restaurant_id)\n"
				+ "ON DELETE CASCADE;";
		
		String reservationsql = " ALTER TABLE reservations ADD CONSTRAINT reservations_ibfk_1\n"
				+ "FOREIGN KEY (restaurant_id) REFERENCES restaurants (restaurant_id)\n"
				+ "ON DELETE CASCADE;";
		
		jdbcTemplate.execute(reviewsql);
		jdbcTemplate.execute(reservationsql);
	
		
	}
}
	

