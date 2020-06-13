package com.backend.library.api.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = false)
	private String firstName;

	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = false)
	private String lastName;

	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = false)
	private int age;

	@NotEmpty(message = "No puede estar vacio")
	@Email(message = "No es una dirección de correo bien formada")
	@Column(nullable = false, unique = true)
	private String email;

	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Borrow> borrows;
	
	
	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = false)
	private String zone;
	
	@Transient
	private Long totalZone;

	/**
	 * Allow to access the user id
	 * 
	 * @return user's id
	 */
	public Long getId() {
		return userId;
	}

	/**
	 * Allow to update the user id
	 * 
	 * @param id updated id
	 */
	public void setId(Long id) {
		this.userId = id;
	}

	/**
	 * Allow to access to user's first name
	 * 
	 * @return user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Allow to update the user's first name
	 * 
	 * @param firstName updated first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Allow to access to the user last name
	 * 
	 * @return user's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Allow to update to the user last name
	 * 
	 * @param lastName updated last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Allow to access to the user age
	 * 
	 * @return user's age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Allow to update the user age
	 * 
	 * @param age updated age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Allow to access the user email
	 * 
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Allow to update the user email
	 * 
	 * @param email uptaded email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Allow to access to the user password
	 * 
	 * @return user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Allow to update to the user id
	 * 
	 * @param password updated password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Allow to access to the user's zone
	 * @return user zone
	 */
	public String getZone() {
		return zone;
	}
	/**
	 * Allow to update user´s zone
	 * 
	 * @param zone updated zone
	 */ 
	public void setZone(String zone) {
		this.zone = zone;
	}
	public Long getTotalZone() {
		return totalZone;
	}
	public void setTotalZone(Long totalZone) {
		this.totalZone = totalZone;
	}

}
