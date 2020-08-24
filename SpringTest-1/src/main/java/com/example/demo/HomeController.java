package com.example.demo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Models.UserDetails;
import com.example.demo.Models.Users;
import com.example.demo.repo.UserDetailsRepo;
import com.example.demo.repo.UserRepo;


@Controller
public class HomeController {
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private Users user;
	List<UserDetails> list=new ArrayList<>();
	@RequestMapping("/home")
	public ModelAndView home(Authentication auth,ModelAndView modelAndView) {
		
		modelAndView.addObject("user",auth.getName());
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			modelAndView.setViewName("Input");
		}
		else
			modelAndView.setViewName("register");
		return modelAndView;

	}
	@RequestMapping("/register")
	public String register(Users user) {
		userRepo.save(user);
		return "login";
	}
	@RequestMapping("/login")
	public String login() {

		return "login";
	}
	@RequestMapping("/")
	public String defaultPage() {
		return "register";
	}

	//	@GetMapping("/details")
	//	public List<UserDetails> details() {
	//		int i=1/0;
	//		return userDetailsRepo.findAll();
	//	}
	//	@GetMapping("/detail/{id}")
	//	public UserDetails detail(@PathVariable("id") int id) {
	//		return userDetailsRepo.findById(id).get();
	//	}
	//	@PostMapping(path="/add",consumes = "application/json")
	//	public List<UserDetails> add(@RequestBody UserDetails userDetails) {
	//		userDetailsRepo.save(userDetails);
	//		return details();
	//	}
}
