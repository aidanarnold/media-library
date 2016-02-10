package com.aidanarnold.media.service;

import com.aidanarnold.media.model.Book;
import com.aidanarnold.media.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * Service layer implementation, managing CRUD
 * @author aidanarnold
 *
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public Book getBook(Integer id) {
		return bookRepository.findOne(id);
	}

	public void upsertBook(Book book) {
		bookRepository.save(book);
	}

	public List<Book> listBooks() {
		return bookRepository.findAll();
	}

	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}
}
