package com.backend.library.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.library.api.model.Book;
import com.backend.library.api.model.Borrow;
import com.backend.library.api.service.IBookService;
import com.backend.library.api.service.IBorrowService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class BorrowController {

	@Autowired
	private IBorrowService borrowService;
	@Autowired
	private IBookService bookService;

	@GetMapping("/all_borrows")
	public List<Borrow> listBorrows() {
		return borrowService.findAllBorrows();
	}

	@GetMapping("/borrows")
	public ResponseEntity<?> showBorrow(@RequestParam("borrow") Long id) {

		Borrow borrow = null;
		Map<String, Object> response = new HashMap<>();

		try {
			borrow = borrowService.findBorrowById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (borrow == null) {
			response.put("mensaje",
					"El préstamo con el ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Borrow>(borrow, HttpStatus.OK);
	}

	@PostMapping("/borrows")
	public ResponseEntity<?> createBorrow(@Valid @RequestBody Borrow borrow, BindingResult result) {

		Borrow borrowNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			borrowNew = borrowService.saveBorrow(borrow);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El préstamo ha sido creado con éxito!");
		response.put("prestamo", borrowNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/borrows/{id}")
	public ResponseEntity<?> updateBorrow(@Valid @RequestBody Borrow borrow, BindingResult result, @PathVariable Long id) {

		Borrow actualBorrow = borrowService.findBorrowById(id);

		Borrow updatedBorrow = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (actualBorrow == null) {
			response.put("mensaje", "Error: no se pudo editar, el préstamo ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

//			actualBorrow.setLastName(user.getLastName());
//			actualBorrow.setFirstName(user.getFirstName());
//			actualBorrow.setEmail(user.getEmail());
//			actualBorrow.setAge(user.getAge());
//
//			updatedBorrow = borrowService.saveUser(actualBorrow);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el préstamo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El préstamo ha sido actualizado con éxito!");
		response.put("prestamo", updatedBorrow);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/borrows/{id}")
	public ResponseEntity<?> deleteBorrow(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			borrowService.deleteBorrow(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el préstamo de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El préstamo eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@PutMapping("/borrow/retun_borrow/{id}")
	public ResponseEntity<?> returnBook(@Valid @RequestBody Borrow borrow, BindingResult result, @PathVariable Long id) {
		
		Book actualBook = bookService.findBookById(id);

		Book updatedBook = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (actualBook == null) {
			response.put("mensaje", "Error: no se pudo devolver el libr, el libro ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			actualBook.setState(true);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el préstamo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El préstamo ha sido actualizado con éxito!");
		response.put("prestamo", updatedBook);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
