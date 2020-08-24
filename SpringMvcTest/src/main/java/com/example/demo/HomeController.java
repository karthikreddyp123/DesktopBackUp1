package com.example.demo;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.UserDetails;

@Controller
public class HomeController {
	List<UserDetails> list=new ArrayList<>();
	@RequestMapping("/")
	public String home() {
		System.out.println("Hello...");
		return "index";
	}
	@RequestMapping("details")
	public String details(UserDetails userDetails,Model m) {
		if(userDetails.getFname()!="") {
		list.add(userDetails);
		}
		m.addAttribute("list", list);
		return "index";
	}
}
