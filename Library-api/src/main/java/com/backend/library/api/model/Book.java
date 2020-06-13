package com.backend.library.api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = false)
	private String name;

	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = false)
	private int categoryAge;
	
	@Column(nullable = false)
	private boolean state;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<Borrow> borrows;

	public Long getId() {
		return bookId;
	}

	public void setId(Long id) {
		this.bookId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryAge() {
		return categoryAge;
	}

	public void setCategoryAge(int categoryAge) {
		this.categoryAge = categoryAge;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

}
