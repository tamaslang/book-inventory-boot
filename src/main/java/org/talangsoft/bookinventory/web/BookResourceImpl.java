package org.talangsoft.bookinventory.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.talangsoft.bookinventory.api.BookDTO;
import org.talangsoft.bookinventory.service.BookService;
import org.talangsoft.rest.devtools.logging.Loggable;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/books")
public class BookResourceImpl implements BookResource, Loggable{

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/{isbn}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BookDTO getByIsbn(@PathVariable String isbn) {
        logger().info("Find book by isbn '{}'",isbn);
        return bookService.findByIsbn(isbn);
    }

    @RequestMapping(
            params = {"author"},
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BookDTO> getByAuthor(@RequestParam(value = "author",required = true) String author){
        String decodedSearchString = UrlUtils.decodeUrlParam(author);
        logger().info("Get book by author '{}'",decodedSearchString);
        return bookService.findByAuthor(decodedSearchString);
    }


    @RequestMapping(
            params = {"title"},
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BookDTO> getByTitle(@RequestParam(value = "title",required = true) String title){
        String decodedSearchString = UrlUtils.decodeUrlParam(title);
        logger().info("Get book by title like '{}'",decodedSearchString);
        return bookService.findByTitleLike(decodedSearchString);
    }



    @RequestMapping(
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BookDTO> getAll() {
        logger().info("Get all books");
        return bookService.findAll();
    }


    @RequestMapping(
            method = RequestMethod.POST,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody @Valid BookDTO book) {
        logger().info("Create book {}",book);

        String isbn = bookService.createNew(book);

        HttpHeaders headers = new HttpHeaders();
        URI location = linkTo(methodOn(this.getClass()).getByIsbn(isbn)).toUri();
        logger().debug("Set header location to: {} ", location);
        headers.setLocation(location);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{isbn}",
            method = RequestMethod.PUT,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable String isbn, @RequestBody @Valid BookDTO book) {
        logger().info("Update book with isbn '{}' to {}",isbn, book);
        bookService.update(isbn,book);
    }


}
