package com.backend.library.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.backend.library.api.model.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
	
	@Query("Select b from Book b where b.state = true")
	List<Book> getAvailablesBooks();
	

}
