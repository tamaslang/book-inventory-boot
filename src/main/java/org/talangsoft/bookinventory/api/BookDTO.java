package org.talangsoft.bookinventory.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.talangsoft.rest.devtools.domain.DTO;

public class BookDTO extends DTO{
    private String isbn;
    private String title;
    private String author;

    @JsonCreator
    public BookDTO(@JsonProperty("isbn") String isbn,@JsonProperty("title") String title,@JsonProperty("author") String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

}
