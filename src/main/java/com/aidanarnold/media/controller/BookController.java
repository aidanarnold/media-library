package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Book;
import com.aidanarnold.media.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BookController {

	private static final Logger logger = LoggerFactory
			.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	/**
	 * Serves up books landing page with list of books
	 * @param model
	 * @return books view
	 */
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String books(Model model) {
		logger.info("Displaying a list of books");
		List<Book> books = bookService.listBooks();
		model.addAttribute("books", books);
		return "books";
	}
	
	/**
	 * Gets a new book to be used with book form
	 * @param model
	 * @return bookForm
	 */
	@RequestMapping(value = "/book/add", method = RequestMethod.GET) 
	public String addbook(Model model) {
		logger.info("Create new book and display form");
		model.addAttribute("book", new Book());
		return "bookForm";
	}

	/**
	 * Adds or updates a book according to whether ID is null
	 * @param b
	 * @param result
	 * @return redirect
	 */
	@RequestMapping(value = "/book/add", method = RequestMethod.POST)
	public String addbook(@Valid Book b, BindingResult result) {
		if (result.hasErrors()) {
			return "bookForm";
		}
		else {
			bookService.upsertBook(b);
		}
		
		return "redirect:/books";
	}

	/**
	 * Deletes a book by ID
	 * @param id
	 * @return redirect
	 */
	@RequestMapping(value = "/book/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable Integer id) {
		Book b = bookService.getBook(id);
		logger.info(b.toString());
		bookService.deleteBook(b);
		return "redirect:/books";
	}

	/**
	 * Gets book by ID to update in book form
	 * @param id
	 * @param model
	 * @return bookForm
	 */
	@RequestMapping(value = "/book/update/{id}", method = RequestMethod.GET)
	public String updateBook(@PathVariable Integer id, Model model) {
		model.addAttribute("book", bookService.getBook(id));
		return "bookForm";
	}
}
