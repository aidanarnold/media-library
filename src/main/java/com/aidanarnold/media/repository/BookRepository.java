package com.aidanarnold.media.repository;

import com.aidanarnold.media.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access interface for Books entity
 * @author aidanarnold
 *
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
}
