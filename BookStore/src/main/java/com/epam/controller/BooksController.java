package com.epam.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.dto.BookDto;
import com.epam.service.BookNotFoundException;
import com.epam.service.BookService;

@RestController
@RequestMapping("api/v1/books")
public class BooksController {

	@Autowired
	private BookService bookService;
	@GetMapping
	public ResponseEntity<List<BookDto>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	@GetMapping("/{id}")
	public ResponseEntity<BookDto> getBookById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(bookService.getBookById(id));
		} catch (BookNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	@PostMapping
	public ResponseEntity<BookDto> addBook(@RequestBody BookDto book){
		BookDto createdBook=bookService.addbook(book);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdBook.getId()).toUri();
		return ResponseEntity.created(location).body(createdBook);
	}
	@PutMapping("/{id}")
	public ResponseEntity<BookDto> updateBook(@PathVariable int id,@RequestBody BookDto book){
		BookDto updatedBook;
		try {
			updatedBook = bookService.updateBook(book);
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedBook.getId()).toUri();
			return ResponseEntity.created(location).body(updatedBook);
		} catch (BookNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id){
		try {
			bookService.deleteBook(id);
			return ResponseEntity.noContent().build();
		} catch (BookNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}	
	}
}
