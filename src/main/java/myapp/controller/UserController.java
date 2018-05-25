package myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import myapp.repository.UserRepository;

public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public String show(Model model, @PathVariable Long id) {
			model.addAttribute("users", userRepository.findAll());
			return "user/listUser";
	}
	
}
