package org.talangsoft.bookinventory.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.talangsoft.bookinventory.api.BookDTO;

import javax.validation.Valid;
import java.util.List;

public interface BookResource {

    /**
     * @api {get} /api/books/{isbn} Get book by isbn code
     * @apiName getByIsbn
     * @apiGroup BookResource
     * @apiDescription Get a book by isbn
     * @apiVersion 1.0.0
     *
     * @apiParam {String} isbn The isbn that identifies the book
     *
     * @apiSuccess {BookDTO} book The book for the isbn
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *         {
     *           "isbn":"isbn1236",
     *           "title":"To Kill a Mockingbird",
     *           "author":"Harper lee"
     *         }
     *
     * @apiError (404) BookNotFound The book was not found
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 404 Not found
     *     {"errorCode":"BOOK_NOT_FOUND",
     *      "errorMessage":"The book was not found.",
     *      "params":{
     *       "isbn":"not-existing-isbn"
     *      }
     *     }
     *
     */
    BookDTO getByIsbn(String isbn);

    /**
     * @api {get} /api/books/?author={author} Find books by author
     * @apiName getByAuthor
     * @apiGroup BookResource
     * @apiDescription Retrieve books by author
     * @apiVersion 1.0.0
     *
     * @apiParam {String} author The author of the book
     *
     * @apiSuccess {List} books The books of the given author
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     [
     *         {
     *           "isbn":"isbn1236",
     *           "title":"To Kill a Mockingbird",
     *           "author":"Harper lee"
     *         },
     *         {
     *           "isbn":"isbn1237",
     *           "title":"Resurrect a Mockingbird",
     *           "author":"Harper lee"
     *         }
     *     ]
     *
     * @apiError (400) UrlEncodingNotSupported The url encoding was not supported
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 400 Not found
     *     {"errorCode":"URL_ENCODING_NOT_SUPPORTED",
     *      "errorMessage":"Url encoding not supported.",
     *      "params":{
     *       "text":"..."
     *      }
     *     }
     *
     */
    List<BookDTO> getByAuthor(@RequestParam(value = "author",required = true) String author);


    /**
     * @api {get} /api/books/?title={title} Find books by title
     * @apiName getByTitle
     * @apiGroup BookResource
     * @apiDescription Retrieve books by title
     * @apiVersion 1.0.0
     *
     * @apiParam {String} title The title of the book
     *
     * @apiSuccess {List} books The books with the given title
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     [
     *         {
     *           "isbn":"isbn1236",
     *           "title":"To Kill a Mockingbird",
     *           "author":"Harper lee"
     *         }
     *     ]
     *
     * @apiError (400) UrlEncodingNotSupported The url encoding was not supported
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 400 Not found
     *     {"errorCode":"URL_ENCODING_NOT_SUPPORTED",
     *      "errorMessage":"Url encoding not supported.",
     *      "params":{
     *       "text":"..."
     *      }
     *     }
     *
     */
    List<BookDTO> getByTitle(@RequestParam(value = "title",required = true) String title);


    /**
     * @api {get} /api/books Find all books
     * @apiName getAll
     * @apiGroup BookResource
     * @apiDescription Find all books
     * @apiVersion 1.0.0
     *
     * @apiSuccess {List} books The books in the database
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     [
     *         {
     *           "isbn":"isbn1235",
     *           "title":"Romeo and Juliet",
     *           "author":"William Shakespeare"
     *         },
     *         {
     *           "isbn":"isbn1236",
     *           "title":"Kill a Mockingbird",
     *           "author":"Harper lee"
     *         }
     *     ]
     *
     */
    List<BookDTO> getAll();


    /**
     * @api {post} /api/books Add a new book
     * @apiName createBook
     * @apiGroup BookResource
     * @apiDescription Add a new book
     * @apiVersion 1.0.0
     *
     * @apiSuccess (201) {String} location The location of the book; sent in header
     *
     * @apiParamExample {json} Request-Example:
     *         {
     *           "isbn":null,
     *           "title":"Romeo and Juliet",
     *           "author":"William Shakespeare"
     *         }
     *
     */
    ResponseEntity<Void> createBook(@RequestBody @Valid BookDTO book);

    /**
     * @api {put} /api/books/isbn Update  book
     * @apiName updateBook
     * @apiGroup BookResource
     * @apiDescription Update the book with the given isbn
     * @apiVersion 1.0.0
     *
     * @apiParamExample {json} Request-Example:
     *         {
     *           "isbn":null,
     *           "title":"Romeo and Juliet",
     *           "author":"William Shakespeare"
     *         }
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *
     * @apiError (404) BookNotFound The book was not found
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 404 Not found
     *     {"errorCode":"BOOK_NOT_FOUND",
     *      "errorMessage":"The book was not found.",
     *      "params":{
     *       "isbn":"not-existing-isbn"
     *      }
     *     }
     */
    void updateBook(String isbn, BookDTO book);
}
