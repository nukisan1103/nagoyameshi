package com.example.nagoyamesi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.PaidRegistForm;
import com.example.nagoyamesi.form.PaidRegistInputForm;
import com.example.nagoyamesi.form.UserEditForm;
import com.example.nagoyamesi.repository.UserRepository;
import com.example.nagoyamesi.security.UserDetailsImpl;
import com.example.nagoyamesi.service.StripeService;
import com.example.nagoyamesi.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;    
    private final UserService userService; 
    private final StripeService stripeService; 
    
    public UserController(UserRepository userRepository, UserService userService
    		, StripeService stripeService) {
        this.userRepository = userRepository;  
        this.userService = userService;
        this.stripeService = stripeService;
    }    
    
    @GetMapping
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {         
        User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());  
        
        model.addAttribute("user", user);
        
        return "user/index";
    }
    
    @GetMapping("/edit")
    public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {        
        User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());  
        UserEditForm userEditForm = new UserEditForm(user.getId(), user.getName(), user.getKana(), user.getAddress(), user.getPhone_number(), user.getEmail());
        
        model.addAttribute("userEditForm", userEditForm);
        
        return "user/edit";
    }    
    @PostMapping("/update")
    public String update(@ModelAttribute @Validated UserEditForm userEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // メールアドレスが変更されており、かつ登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
        if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
            bindingResult.addError(fieldError);                       
        }
        
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        
        userService.update(userEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");
        
        return "redirect:/user";
    }    
    @GetMapping("/paidregistration")
    public String paidRegistration(Model model) {         
       
        model.addAttribute("paidRegistInputForm", new PaidRegistInputForm());
        
        return "user/paidregist";
    }
    @PostMapping("/upgradeConfirm")
    public String upgradeConfirm(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    		@ModelAttribute PaidRegistInputForm paidRegistInputForm
    		,Model model, HttpServletRequest httpServletRequest) {         
    	
    	User user = userDetailsImpl.getUser();
    	
    	PaidRegistForm paidRegistForm = new PaidRegistForm(user.getId(),paidRegistInputForm.getNominee(),paidRegistInputForm.getCard_number()
    			,paidRegistInputForm.getSec_number(),paidRegistInputForm.getCard_type(),paidRegistInputForm.getPeriod_year()
    			,paidRegistInputForm.getPeriod_month());
    	
    	 String sessionId = stripeService.createStripeSession(paidRegistForm, httpServletRequest);
    	 
    	   // セッションIDをログに出力
    	    System.out.println("Generated sessionId: " + sessionId);

    	
    	 model.addAttribute("paidRegistForm", paidRegistForm);
    	 model.addAttribute("sessionId", sessionId);
    	 return "user/paidconfirm";
    	     
    }
}