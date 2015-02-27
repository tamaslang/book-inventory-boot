package org.talangsoft.bookinventory.service;

import org.springframework.transaction.annotation.Transactional;
import org.talangsoft.bookinventory.api.BookDTO;

import java.util.List;

public interface BookService {
    @Transactional
    String createNew(BookDTO bookDTO);

    @Transactional
    void update(String isbn, BookDTO bookDTO);

    BookDTO findByIsbn(String isbn);

    List<BookDTO> findByAuthor(String author);

    List<BookDTO> findByTitleLike(String title);

    List<BookDTO> findAll();

    void delete(String isbn);
}
