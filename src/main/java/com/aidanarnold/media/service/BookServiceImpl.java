package com.aidanarnold.media.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aidanarnold.media.dao.BookDao;
import com.aidanarnold.media.model.Book;

/***
 * Service layer implementation, managing CRUD
 * @author aidanarnold
 *
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	public Book getBook(Integer id) {
		return bookDao.getBook(id);
	}

	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	public List<Book> listBooks() {
		return bookDao.list();
	}

	public void deleteBook(Book book) {
		bookDao.deleteBook(book);
		
	}

	public void updateBook(Book book) {
		bookDao.updateBook(book);
		
	}

}
