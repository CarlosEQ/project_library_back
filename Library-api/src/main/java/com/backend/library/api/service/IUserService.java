package com.backend.library.api.service;

import java.util.List;

import com.backend.library.api.model.User;

public interface IUserService {

	public List<User> findAllUsers();
	
	public User findUserById(Long id);
	
	public User saveUser(User user);
	
	public void deleteUser(Long id);
	
	public User findUserToLogin(String email, String password);
	
	public List<User> getByZones();
	
	public Long coutByZones(String zone);

}
