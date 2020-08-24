package com.epam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.BookDto;
import com.epam.model.Book;
import com.epam.repository.BookRepository;
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	 
	public List<BookDto> getAllBooks(){
		return bookRepository.findAll().stream().map(book->modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
	}
	public BookDto addbook(BookDto bookDto) {
		Book book=modelMapper.map(bookDto, Book.class);
		return modelMapper.map(bookRepository.save(book), BookDto.class);
	}
	public BookDto getBookById(long id) throws BookNotFoundException {
		return modelMapper.map(bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("Book not found")), BookDto.class);
	}
	public BookDto updateBook(BookDto bookDto) throws BookNotFoundException {
		this.getBookById(bookDto.getId());
		Book book=modelMapper.map(bookDto, Book.class);
		return modelMapper.map(bookRepository.save(book), BookDto.class);
	}
	public void deleteBook(int id) throws BookNotFoundException {
		Book book=modelMapper.map(this.getBookById(id), Book.class);
		bookRepository.delete(book);
	}
}
