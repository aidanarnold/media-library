package com.aidanarnold.media.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import com.aidanarnold.media.model.Book;

/**
 * DAO Implementation for Book entity 
 * @author aidanarnold
 *
 */
@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	
	private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Book getBook(Integer id) {
		Book book = entityManager.find(Book.class, id);
		logger.info("Returning the following: " + book.toString());
		return entityManager.find(Book.class, id);
	}

	public List<Book> list() {
		logger.info("List of books from datastore.");
		String hql = "from Book";
		return entityManager.createQuery(hql, Book.class).getResultList();
	}

	public void addBook(Book book) {
		logger.info("Adding book.");
		entityManager.persist(book);
	}
	
	public void updateBook(Book book) {
		logger.info("Updating book.");
		entityManager.merge(book);
	}

	public void deleteBook(Book book) {
		logger.info("Deleting book");
		entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
	}
}
