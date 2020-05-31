package com.unla.Grupo16OO22020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unla.Grupo16OO22020.helpers.ViewRouteHelper;
import com.unla.Grupo16OO22020.services.implementation.UserService;


@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/index";
	}
	
	@GetMapping("/mostrarUsuario")
	@ResponseBody
	 public String mostrarUsuario()
	 {	
		return userService.mostrarUsuario();
	  
	 }	
}
