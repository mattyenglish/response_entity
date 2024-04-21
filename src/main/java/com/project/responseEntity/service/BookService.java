package com.project.responseEntity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.responseEntity.model.Book;
import com.project.responseEntity.repo.BookRepo;

@Service
public class BookService {
	@Autowired
	private BookRepo bookRepo;
	
	//get all books
	public List<Book> getAllBooks(){
		return bookRepo.findAll();
	}
	
	//get book by id
	public Optional<Book> getBookById(int id){
		return bookRepo.findById(id);
	}
	
	//save book/create book
	public void saveBook(Book book) {
		 bookRepo.save(book);
	}
	
	//edit/update book
	public Optional<Book> updateBook(int id){
		return bookRepo.findById(id);
	}
	
	//delete a book
	public void deleteBook(int id){
		 bookRepo.deleteById(id);
	}

}
