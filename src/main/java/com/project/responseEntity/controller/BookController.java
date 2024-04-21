package com.project.responseEntity.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.responseEntity.model.Book;
import com.project.responseEntity.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	// get all books
	@GetMapping("/mybooks")
	public ResponseEntity<?> getAllBooks() {
		List<Book> book = bookService.getAllBooks();
		if (book.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Books found!!!");
		}

		return ResponseEntity.status(HttpStatus.OK).body(book);
	}

	// get book by id
	@GetMapping("/id/{bookId}")
	public ResponseEntity<?> getBookById(@PathVariable int bookId) {
		Optional<Book> book = bookService.getBookById(bookId);
		if (book.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(book);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Book found with bookId = " + bookId);
	}

	// create or save book
	@PostMapping("/save")
	public ResponseEntity<String> saveBook(@RequestBody Book book) {

		// checking if book object is null
		if (book == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book Object is null");
		}

		// checking if book name is null
		if ((book.getBookName() == null) || book.getBookName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book title is required!!!");
		}
		// checking if book price is null
		if ((book.getBookPrice() <= 0)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book price should be greater than zero!!!");
		}

		// checking if book author is null
		if ((book.getBookAuthor() == null) || book.getBookAuthor().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book Author is required!!!");
		}

		// checking if language is null
		if ((book.getLanguage() == null) || book.getLanguage().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book Language is required!!!");
		}

		// checking if pages greater than 1
		if ((book.getPages() < 1)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book pages should be greater than 1!!!");
		}

		// checking if description is null
		if ((book.getDescription() == null) || book.getDescription().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book description is required!!!");
		}

		// if not save everything
		bookService.saveBook(book);
		return ResponseEntity.status(HttpStatus.OK).body("Book is created Successfully!!!");
	}

	// update book

	@PutMapping("/update/id/{bookId}")
	public ResponseEntity<String> editBook(@PathVariable("bookId") Integer bookId, @RequestBody Book updatedBook) {
		try {
			// If request body is missing
			if (updatedBook == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body is missing");
			}

			// Check if the provided ID is valid
			if (bookId == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book ID is required");
			}

			// Check if book is present with provided id
			Book old = bookService.getBookById(bookId).orElse(null);

			// Check if existing book is present
			if (old == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book is not present with id = " + bookId);
			}

			// If book is present, start updating it
			// Updating name
			if (updatedBook.getBookName() != null && !updatedBook.getBookName().isEmpty()) {
				old.setBookName(updatedBook.getBookName());
			}

			// Updating author
			if (updatedBook.getBookAuthor() != null && !updatedBook.getBookAuthor().isEmpty()) {
				old.setBookAuthor(updatedBook.getBookAuthor());
			}

			// Updating price
			if (updatedBook.getBookPrice() != null && updatedBook.getBookPrice() != 0) {
				old.setBookPrice(updatedBook.getBookPrice());
			}

			// Updating language
			if (updatedBook.getLanguage() != null && !updatedBook.getLanguage().isEmpty()) {
				old.setLanguage(updatedBook.getLanguage());
			}
			// Updating pages
			if (updatedBook.getPages() != null && updatedBook.getPages() != 0) {
				old.setPages(updatedBook.getPages());
			}

			// update description
			if (updatedBook.getDescription() != null && !updatedBook.getDescription().isEmpty()) {
				old.setDescription(updatedBook.getDescription());
			}

			// everything is present update the data
			bookService.saveBook(old);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while updating the book");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully!!!");
	}

	@DeleteMapping("/delete/id/{bookId}")
	public ResponseEntity<String> deleteBookById(@PathVariable int bookId) {

		// get the book id
		Optional<Book> book = bookService.getBookById(bookId);
		// check if book present if yes then delete it
		if (book.isPresent()) {
			bookService.deleteBook(bookId);
			return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully!!!");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Book found with bookId = " + bookId);

	}
}
