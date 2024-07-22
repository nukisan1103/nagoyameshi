package com.example.nagoyamesi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyamesi.entity.Role;
import com.example.nagoyamesi.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {	
   public User findByEmail(String email);
    public Page<User> findByNameLikeOrKanaLike(String nameKeyword, String furiganaKeyword, Pageable pageable);
    public Page<User> findByRole(Role roleKeyword, Pageable pageable);
	public List<User> findByRole(Role role);
}
