package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public int getMatchCount(String username, String password) {
		return userDao.getMatchCount(username, password);
	}

	public User findUserbyUsername(final String username) {
		return userDao.findUserbyUsername(username);
	}

	public void updateLoginInfo(User user) {
		userDao.updateLoginInfo(user);
	}
}