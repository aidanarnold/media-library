package com.aidanarnold.media.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {

    public static final String AUTHOR = "Paulo Bacigalupi";
    public static final String TITLE = "The Windup Girl";
    Book book = new Book();

    @Before
    public void init() {
        book.setId(1);
        book.setAuthor(AUTHOR);
        book.setTitle(TITLE);
        book.setRead(true);
    }

    @Test
    public void shouldConstructBookWithOnlyTitle() {
        Book constructedBook = new Book(TITLE);

        assertThat(constructedBook.getTitle()).isEqualTo(book.getTitle());
    }

    @Test
    public void shouldConstructBook()
    {
        Book constructedBook = new Book(TITLE, AUTHOR, true);

        assertThat(constructedBook.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(constructedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(constructedBook.isRead()).isEqualTo(book.isRead());
    }

}
