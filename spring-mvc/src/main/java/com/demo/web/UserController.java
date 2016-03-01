package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.domain.User;
import com.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(@RequestParam("user.username") String username, @RequestParam("user.password") String password) {
		User user2 = userService.findUserbyUsername(username);
		return user2 == null ? "index" : "hello";
	}
}