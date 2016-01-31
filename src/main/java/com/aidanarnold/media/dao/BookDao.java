package com.aidanarnold.media.dao;

import java.util.List;

import com.aidanarnold.media.model.Book;
 
/**
 * Data access interface for Books entity
 * @author aidanarnold
 *
 */
public interface BookDao {
 
	public Book getBook(Integer id);
	public List<Book> list();
	public void addBook(Book book);
	public void deleteBook(Book book);
	public void updateBook(Book book);
	
}
