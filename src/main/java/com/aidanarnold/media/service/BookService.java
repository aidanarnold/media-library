package com.aidanarnold.media.service;

import java.util.List;

import com.aidanarnold.media.model.Book;

/***
 * Service layer interface, managing CRUD
 * @author aidanarnold
 *
 */
public interface BookService {
	
 public Book getBook(Integer id);
 
 public void addBook(Book book);

 public List<Book> listBooks();
 
 public void deleteBook(Book book);
 
 public void updateBook(Book book);
 
}
