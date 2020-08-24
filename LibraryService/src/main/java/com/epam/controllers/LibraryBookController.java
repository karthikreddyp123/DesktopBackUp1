package com.epam.controllers;

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
import org.springframework.web.client.RestTemplate;

import com.epam.models.Book;
import com.epam.services.BookServiceClient;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping("library/books")
public class LibraryBookController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private BookServiceClient bookServiceClient;
	
	@GetMapping("/booksrest")
	public ResponseEntity<List<Book>> getBooksRestTemplate(){
		Application application=eurekaClient.getApplication("book-service");
		InstanceInfo instanceInfo=application.getInstances().get(0);
		String url="http://"+instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "api/v1/books";
		@SuppressWarnings("unchecked")
		List<Book> books=restTemplate.getForObject(url.trim(), List.class);
		return ResponseEntity.ok(books);
	}
	@GetMapping
	public ResponseEntity<List<Book>> getBooks(){
		return bookServiceClient.getBooks();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) {
		return bookServiceClient.getBookById(id);
	}
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		return bookServiceClient.addBook(book);		
	}
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id,@RequestBody Book book){
		return bookServiceClient.updateBook(id, book);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id){
		return bookServiceClient.deleteBook(id);
	}
}
