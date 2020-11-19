package ePharma.springboot.controller;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ePharma.springboot.model.User;
import ePharma.springboot.service.UserService;
import ePharma.springboot.service.UserServiceImpl;




@Controller
public class LoginController {

	

	@Autowired
	UserService service;

	@Autowired
	UserServiceImpl serviceImpl;

	@GetMapping(value = "/login")
	public String userLogin() {
		return "login";
	}
	@GetMapping(value = "/date")
	public String user6Login() {
		return "date";
	}
	
	@GetMapping(value = "/thanks")
	public String userLo5gin() {
		return "medi";
	}
	
	
	@GetMapping(value = "/home")
	public String us5erLogin() {
		return "hello";
	}

	@PostMapping(value = "/login")
	public String Login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, RedirectAttributes r, ModelMap model) {
		boolean success = serviceImpl.authenticateUser(email, password);

		if(email.length() < 1) {
			r.addFlashAttribute("error", "Email field cannot be blank.");
			return "redirect:/login";
		}else if (password.length() < 1) {
			r.addFlashAttribute("error", "Please enter your password.");
			return "redirect:/login";
		}else if (!success) {
			r.addFlashAttribute("error", "Email and password do not match.");
			return "redirect:/login";
		}else {
			User user = serviceImpl.findByEmail(email);
			model.put("email", email);
			Long id = user.getId();
			session.setAttribute("userid", id);
			return "redirect:/thanks";
		}
	}

	@RequestMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		return "register";
	}


	@PostMapping(value = "/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result,RedirectAttributes r,
			@RequestParam("email") String email,@RequestParam("password") String password ) 
	{
		
	
		 if (password.length() < 5) {
			r.addFlashAttribute("error", "Password cant be less than 5");
			return "redirect:/register";}
		if (result.hasErrors()) 
		{
			return "register";
		} 
		else {
			
			
			serviceImpl.registerUser(user);
			
			return "redirect:/login";
		}
	}
}
