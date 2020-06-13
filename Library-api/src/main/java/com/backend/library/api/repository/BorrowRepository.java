package com.backend.library.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.backend.library.api.model.Borrow;

public interface BorrowRepository extends CrudRepository<Borrow, Long> {

}
