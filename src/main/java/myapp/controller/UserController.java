package myapp.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import myapp.form.UserForm;
import myapp.model.User;
import myapp.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/user/l")
	public String userList(Model model) {
		model.addAttribute("userList", userRepository.findAll());
		model.addAttribute("userForm", new UserForm());
		return "user/listUser";
	}
	
	
	@GetMapping("/user/a")
	public String userAddGet(Model model) {
//		model.addAttribute("userList", userRepository.findAll());
		model.addAttribute("userForm", new UserForm());
		return "user/addUser";
	}
	
	@PostMapping("/user/add")
	public String userAdd(@Valid User user, BindingResult result) {
			if (result.hasErrors()) {
	            return "user/listUser";
	        }
			else {
	      // Create a new user 
				User u = new User();
	      // Add to user u : 
				u.setFirstname(user.getFirstname());
				u.setLastname(user.getLastname());
				u.setUsername(user.getUsername());
				u.setPassword(user.getPassword());
	      // Save user added:
				userRepository.save(u);
		      // Return to the list of user
		      return "redirect:/user";
			}
	}
	
	@GetMapping("/user/{id}")
	public String userShow(Model model, @PathVariable Long id) {
			model.addAttribute("userForm", userRepository.findById(id));
			return "user/editUser";
	}
	
	@PostMapping("/users/edit")
	public String Useredit(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "user/listUser";
		} else {
			User s = (userRepository.findById(user.getId())).get();
			s.setId(user.getId());
			s.setFirstname(user.getFirstname());
			s.setLastname(user.getLastname());
			s.setUsername(user.getUsername());
			s.setPassword(user.getPassword());
			userRepository.save(s);
			return "redirect:/user";
		}
	}
	
	@GetMapping("/users/delete/{id}")
	public String delete(Model model, @PathVariable Long id) {
		User userToDelete = (userRepository.findById(id)).get();
		userRepository.delete(userToDelete);
		return "user/listUser";
	}


}
