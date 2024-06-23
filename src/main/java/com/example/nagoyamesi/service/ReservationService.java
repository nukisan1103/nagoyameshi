package com.example.nagoyamesi.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyamesi.entity.Reservation;
import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.ReservationRegisterForm;
import com.example.nagoyamesi.repository.ReservationRepository;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.repository.UserRepository;

@Service
public class ReservationService {
	  private final ReservationRepository reservationRepository;  
	     private final RestaurantRepository restaurantRepository;  
	     private final UserRepository userRepository;  
	     
	     public ReservationService(ReservationRepository reservationRepository,RestaurantRepository restaurantRepository, UserRepository userRepository) {
	         this.reservationRepository = reservationRepository;  
	         this.restaurantRepository = restaurantRepository;  
	         this.userRepository = userRepository;  
	     }    
	     
	     @Transactional
	     public void create(ReservationRegisterForm reservationRegisterForm) { 
	         Reservation reservation = new Reservation();
	         Restaurant restaurant = restaurantRepository.getReferenceById(reservationRegisterForm.getRestaurantId());
	         User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
	         String checkinDate = reservationRegisterForm.getReservationDate();
	        
	                 
	         reservation.setRestaurant(restaurant);
	         reservation.setUser(user);
	         reservation.setReservationDateTime(checkinDate);
	         reservation.setReservationTime(reservationRegisterForm.getReservationTime());
	         reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
	       
	         
	         reservationRepository.save(reservation);
	     }    
	
    // 宿泊人数が定員以下かどうかをチェックする
    public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
        return numberOfPeople <= capacity;
    }
    
    // 宿泊料金を計算する
    public Integer calculateAmount(LocalDate checkinDate, LocalDate checkoutDate, Integer price) {
        long numberOfNights = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
        int amount = price * (int)numberOfNights;
        return amount;
    }    
}