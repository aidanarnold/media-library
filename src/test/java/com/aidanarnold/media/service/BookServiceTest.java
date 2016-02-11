package com.aidanarnold.media.service;

import com.aidanarnold.media.model.Book;
import com.aidanarnold.media.repository.BookRepository;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceTest {

    @Injectable
    private BookRepository bookRepository;

    @Tested
    private BookServiceImpl bookService;

    Book book1 = new Book("A Title", "An Author", true);
    Book book2 = new Book("Another Title", "Another Author", true);
    Book book3 = new Book("A New Title", "A New Author", false);
    List<Book> books = new ArrayList<>();

    @Before
    public void init() {
        bookService = new BookServiceImpl();
        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    @Test
    public void testShouldGetBook() {

        new NonStrictExpectations() {{
            bookRepository.findOne(anyInt);
            result = book1;
        }};

        Book book = bookService.getBook(1);

        new Verifications() {{
            bookRepository.findOne(1);
            assertThat(book.getTitle()).isEqualTo(book1.getTitle());
            assertThat(book.getAuthor()).isEqualTo(book1.getAuthor());
            assertThat(book.isRead()).isEqualTo(book1.isRead());
        }};
    }

    @Test
    public void testShouldGetBooks() {

        new NonStrictExpectations() {{
            bookRepository.findAll();
            result = books;
        }};

        List<Book> booksReturned = bookService.listBooks();

        new Verifications() {{
           bookRepository.findAll();
           assertThat(booksReturned.size()).isEqualTo(books.size());
        }};
    }

    @Test
    public void testShouldUpsertBook() {
        Book book4 = new Book("A Book For Testing", "Jay Mockit", false);

        bookService.upsertBook(book4);

        new Verifications() {{
            bookRepository.save(book4);
        }};
    }

    @Test
    public void testShouldDeleteBook() {

        bookService.deleteBook(book3);

        new Verifications() {{
            bookRepository.delete(book3);
        }};
    }
}
