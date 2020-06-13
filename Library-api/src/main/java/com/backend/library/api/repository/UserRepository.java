package com.backend.library.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.backend.library.api.model.User;


public interface UserRepository extends CrudRepository<User, Long>{

	@Query("SELECT u FROM User u WHERE u.email = :email and u.password = :password")
	User findUserToLogin(
	  @Param("email") String email, 
	  @Param("password") String name);
	
	@Query("SELECT distinct u from User u join Borrow b on u.id = b.user.id")
	List<User> getByZones();
	
	@Query("SELECT count(u) from User u join Borrow b on u.id = b.user.id where u.zone = :zone")
	Long  countByZones(@Param("zone") String zone);
	
}
