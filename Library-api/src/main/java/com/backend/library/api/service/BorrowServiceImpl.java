package com.backend.library.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.library.api.model.Borrow;
import com.backend.library.api.repository.BorrowRepository;

@Service
public class BorrowServiceImpl implements IBorrowService {

	@Autowired
	private BorrowRepository borrowRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Borrow> findAllBorrows() {
		return (List<Borrow>) borrowRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Borrow findBorrowById(Long id) {
		return borrowRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Borrow saveBorrow(Borrow borrow) {
		return borrowRepository.save(borrow);
	}

	@Override
	@Transactional
	public void deleteBorrow(Long id) {
		borrowRepository.deleteById(id);
	}

}
