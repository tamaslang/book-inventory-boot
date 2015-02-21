package org.talang.bookinventory.api;

import org.talang.rest.devtools.domain.DTO;

public class BookDTO extends DTO{
    private String isbn;

    private String title;

    private String author;

    public BookDTO() {
    }

    public BookDTO(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
