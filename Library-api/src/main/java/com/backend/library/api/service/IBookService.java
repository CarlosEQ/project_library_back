package com.backend.library.api.service;

import java.util.List;

import com.backend.library.api.model.Book;

public interface IBookService {

	public List<Book> findAllBooks();

	public Book findBookById(Long id);

	public Book saveBook(Book book);

	public void deleteBook(Long id);
	
	public List<Book> getAvailablesBooks();

}
