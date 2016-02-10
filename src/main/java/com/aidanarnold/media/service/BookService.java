package com.aidanarnold.media.service;

import com.aidanarnold.media.model.Book;

import java.util.List;

/***
 * Service layer interface, managing CRUD
 * @author aidanarnold
 *
 */
public interface BookService {
	
 Book getBook(Integer id);
 
 void upsertBook(Book book);

 List<Book> listBooks();
 
 void deleteBook(Book book);
}
