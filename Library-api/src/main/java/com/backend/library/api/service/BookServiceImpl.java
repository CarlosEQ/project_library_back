package com.backend.library.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.library.api.model.Book;
import com.backend.library.api.repository.BookRepository;


@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Book findBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	@Transactional
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> getAvailablesBooks() {
		return bookRepository.getAvailablesBooks();
	}

}
