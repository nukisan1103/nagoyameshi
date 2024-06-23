package com.example.nagoyamesi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyamesi.entity.PaidRegist;
import com.example.nagoyamesi.entity.User;

public interface PaidRegistRepository extends JpaRepository<PaidRegist, Integer> {
	public Page<PaidRegist> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
	}
