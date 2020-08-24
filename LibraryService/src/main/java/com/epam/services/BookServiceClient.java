package com.epam.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.models.Book;

@FeignClient(name = "book-service")
@Component
@RequestMapping("api/v1/books")
public interface BookServiceClient {

	@GetMapping
	ResponseEntity<List<Book>> getBooks();

	@GetMapping("/{id}")
	ResponseEntity<Book> getBookById(@PathVariable long id);

	@PostMapping
	ResponseEntity<Book> addBook(@RequestBody Book book);

	@PutMapping("/{id}")
	ResponseEntity<Book> updateBook(@PathVariable long id,@RequestBody Book book);

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteBook(@PathVariable long id);
}
