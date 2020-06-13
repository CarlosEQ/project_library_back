package com.backend.library.api.service;

import java.util.List;

import com.backend.library.api.model.Borrow;

public interface IBorrowService {
	
	public List<Borrow> findAllBorrows();

	public Borrow findBorrowById(Long id);

	public Borrow saveBorrow(Borrow borrow);

	public void deleteBorrow(Long id);
}
