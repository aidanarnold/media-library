package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Book;
import com.aidanarnold.media.service.BookService;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookControllerTest {

    @Tested
    BookController bookController;

    @Injectable
    BookService bookService;

    Book book1 = new Book("A Title", "An Author", true);
    Book book2 = new Book("Another Title", "Another Author", true);
    Book book3 = new Book("A New Title", "A New Author", false);
    List<Book> books = new ArrayList<>();

    @Before
    public void init() {
        bookController = new BookController();
        book1.setId(1);
        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    @Test
    public void testShouldGetBook() {
        new NonStrictExpectations() {{
            bookService.getBook(anyInt);
            result = book1;
        }};

        Book bookReturned = bookController.getBook(1);

        new Verifications() {{
            bookService.getBook(1);
            assertThat(bookReturned).isNotNull();
            assertThat(bookReturned.getTitle()).isEqualTo(book1.getTitle());
            assertThat(bookReturned.getAuthor()).isEqualTo(book1.getAuthor());
            assertThat(bookReturned.isRead()).isEqualTo(book1.isRead());
        }};
    }

    @Test
    public void testShouldGetBooks() {

        new NonStrictExpectations() {{
            bookService.listBooks();
            result = books;
        }};

        List<Book> booksReturned = bookController.books();

        new Verifications() {{
            assertThat(booksReturned.size()).isEqualTo(3);
            assertThat(booksReturned.get(0).getAuthor()).isEqualTo(books.get(0).getAuthor());
            assertThat(booksReturned.get(0).getTitle()).isEqualTo(books.get(0).getTitle());
            assertThat(booksReturned.get(0).isRead()).isEqualTo(books.get(0).isRead());
            assertThat(booksReturned.get(1).getAuthor()).isEqualTo(books.get(1).getAuthor());
            assertThat(booksReturned.get(1).getTitle()).isEqualTo(books.get(1).getTitle());
            assertThat(booksReturned.get(1).isRead()).isEqualTo(books.get(1).isRead());
            assertThat(booksReturned.get(2).getAuthor()).isEqualTo(books.get(2).getAuthor());
            assertThat(booksReturned.get(2).getTitle()).isEqualTo(books.get(2).getTitle());
            assertThat(booksReturned.get(2).isRead()).isEqualTo(books.get(2).isRead());
        }};
    }

    @Test
    public void testShouldAddBook() {
        bookController.addBook(book2);

        new Verifications() {{
            bookService.upsertBook(book2);
        }};
    }

    @Test
    public void testShouldUpdateBook() {

        new NonStrictExpectations() {{
            bookService.getBook(anyInt);
            result = book1;
        }};

        book1.setTitle("Book: A Title");

        bookController.updateBook(1, book1);

        new Verifications() {{
            bookService.upsertBook(book1);
        }};
    }


    @Test
    public void testShouldDeleteBook() {

        new NonStrictExpectations() {{
            bookService.getBook(anyInt);
            result = book1;
        }};

        bookController.deleteBook(1);

        new Verifications() {{
            bookService.deleteBook(book1);
        }};
    }
}
