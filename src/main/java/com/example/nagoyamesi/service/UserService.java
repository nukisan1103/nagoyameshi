package com.example.nagoyamesi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyamesi.entity.PaidRegist;
import com.example.nagoyamesi.entity.Role;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.PaidRegistForm;
import com.example.nagoyamesi.form.SignupForm;
import com.example.nagoyamesi.form.UserEditForm;
import com.example.nagoyamesi.repository.PaidRegistRepository;
import com.example.nagoyamesi.repository.RoleRepository;
import com.example.nagoyamesi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PaidRegistRepository paidRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder
    		,PaidRegistRepository paidRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;        
        this.passwordEncoder = passwordEncoder;
        this.paidRepository = paidRepository;
    }    
    
    @Transactional
    public User create(SignupForm signupForm) {
        User user = new User();
        Role role = roleRepository.findByName("ROLE_GENERAL");
        
        user.setName(signupForm.getName());
        user.setKana(signupForm.getKana());
        user.setAddress(signupForm.getAddress());
        user.setPhone_number(signupForm.getPhone_number());
        user.setEmail(signupForm.getEmail());
        user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        user.setRole(role);
        user.setEnabled(false);       
        
        return userRepository.save(user);
    }    
    @Transactional
    public void update(UserEditForm userEditForm) {
    	//getReferenceById関数で、編集対象のユーザーのIDを取得する
        User user = userRepository.getReferenceById(userEditForm.getId());
        
        user.setName(userEditForm.getName());
        user.setKana(userEditForm.getKana());     
        user.setAddress(userEditForm.getAddress());
        user.setPhone_number(userEditForm.getPhone_number());
        user.setEmail(userEditForm.getEmail());      
        
        userRepository.save(user);
    }    
    
    @Transactional
    public void upgrade(PaidRegistForm paidRegistForm,User upgradeUser) {
    	//getReferenceById関数で、編集対象のユーザーのIDを取得する
    	PaidRegist paid = new PaidRegist();
    	
    	User user = userRepository.getReferenceById(upgradeUser.getId());
    	Role role = roleRepository.findByName("ROLE_SUBSCRIBER");
        
    	user.setRole(role);
    	paid.setUser(upgradeUser);
        paid.setNominee(paidRegistForm.getNominee());     
        paid.setCard_type(paidRegistForm.getCard_type());
        paid.setPeriod_year(paidRegistForm.getPeriod_year());
        paid.setPeriod_month(paidRegistForm.getPeriod_month());      
        
        paidRepository.save(paid);
    }    
  

    
 // メールアドレスが登録済みかどうかをチェックする
    public boolean isEmailRegistered(String email) {
        User user = userRepository.findByEmail(email);  
        return user != null;
    }
    // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
         public boolean isSamePassword(String password, String passwordConfirmation) {
             return password.equals(passwordConfirmation);
         }     
         // ユーザーを有効にする
              @Transactional
              public void enableUser(User user) {
                  user.setEnabled(true); 
                  userRepository.save(user);
              }    
              // メールアドレスが変更されたかどうかをチェックする
              public boolean isEmailChanged(UserEditForm userEditForm) {
                  User currentUser = userRepository.getReferenceById(userEditForm.getId());
                  return !userEditForm.getEmail().equals(currentUser.getEmail());      
              }  
             

	
}
