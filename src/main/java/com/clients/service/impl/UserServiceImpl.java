/**
 * 
 */
package com.clients.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clients.dao.UserDao;
import com.clients.model.UserDetails;
import com.clients.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public List<UserDetails> getUserDetails() {
		return userDao.getUserDetails();

	}

}
