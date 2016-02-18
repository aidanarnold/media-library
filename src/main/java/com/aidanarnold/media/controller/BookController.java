package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Book;
import com.aidanarnold.media.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * Gets all books
     *
     * @return books
     */
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> books() {
        return bookService.listBooks();
    }

    /**
     * Adds new book
     *
     * @param book
     * @return redirect
     */
    @RequestMapping(value = "/books", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addBook(@RequestBody Book book) {
        System.out.println(book);
        bookService.upsertBook(book);
    }

    /**
     * Gets book by ID
     *
     * @return books
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBook(@PathVariable Integer id) {
        return bookService.getBook(id);
    }

    /**
     * Updates book
     *
     * @return books
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateBook(@PathVariable Integer id, @RequestBody Book book) {
        //Book book = bookService.getBook(id);
        bookService.upsertBook(book);
    }

    /**
     * Deletes a book by ID
     *
     * @param id
     * @return redirect
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Integer id) {
        Book book = bookService.getBook(id);
        logger.info("Attempting to delete: " + book.getTitle());
        bookService.deleteBook(book);
    }
}
