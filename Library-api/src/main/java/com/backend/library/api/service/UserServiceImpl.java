package com.backend.library.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.library.api.model.User;
import com.backend.library.api.repository.UserRepository;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public User saveUser(User book) {
		return userRepository.save(book);
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findUserToLogin(String email, String password) {
		return userRepository.findUserToLogin(email, password);
	}

	@Override
	public List<User> getByZones() {
		return userRepository.getByZones();
	}

	@Override
	public Long coutByZones(String zone) {
		return userRepository.countByZones(zone);
	}
	
	
			

}
