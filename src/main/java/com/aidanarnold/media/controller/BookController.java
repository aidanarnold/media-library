package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Book;
import com.aidanarnold.media.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("books")
public class BookController {

	private static final Logger logger = LoggerFactory
			.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	/**
	 * Serves up books landing page with list of books
	 * @return books
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Book getBook(@PathVariable Integer id) {
		logger.info("Getting a book");
		return bookService.getBook(id);
	}

	/**
	 * Serves up books landing page with list of books
	 * @return books
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Book> books() {
		logger.info("Displaying a list of books");
		return bookService.listBooks();
	}

	/**
	 * Adds or updates a book
	 * @param book
	 * @return redirect
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addBook(Book book) {
		bookService.upsertBook(book);
	}

	/**
	 * Deletes a book by ID
	 * @param id
	 * @return redirect
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void deleteBook(@PathVariable Integer id) {
		Book book = bookService.getBook(id);
		logger.info("Attempting to delete: " + book.getTitle());
		bookService.deleteBook(book);
	}
}
