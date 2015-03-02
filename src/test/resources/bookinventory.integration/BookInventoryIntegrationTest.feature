@restApiIntegration
Feature: Example Resource Integration Test

  Scenario: create a book
    Given the web context is set
    Given the db is empty
    Given the isbn gateway is mocked to success
    When client request POST /api/books with json data:
    """
    {"isbn":null,"title":"my book","author":"me"}
    """
    Then the response code should be 201
    Then the following header should present "Location" with value "http://localhost/api/books/isbn1234"

  Scenario: create a book gateway error
    Given the web context is set
    Given the db is empty
    Given the isbn gateway is mocked to error
    When client request POST /api/books with json data:
    """
    {"isbn":null,"title":"my book","author":"me"}
    """
    Then the response code should be 500
    Then the result json should be:
    """
    {"errorCode":"GENERAL_GATEWAY_ERROR","errorMessage":"Internal Server Error","params":{"statusText":"Internal Server Error","message":"500 Internal Server Error"}}
    """

  Scenario: find by isbn
    Given the web context is set
    Given the db is empty
    Given the following books exist:
    | isbn     |    title              |    author            |
    | isbn1234 | Hamlet                |  William Shakespeare |
    | isbn1235 | Romeo and Juliet      |  William Shakespeare |
    | isbn1236 | To Kill a Mockingbird |  Harper lee          |
    When client request GET /api/books/isbn1236
    Then the response code should be 200
    Then the result json should be:
    """
    {"isbn":"isbn1236","title":"To Kill a Mockingbird","author":"Harper lee"}
    """

  Scenario: find by isbn -> no result
    Given the web context is set
    Given the db is empty
    When client request GET /api/books/not-existing-isbn
    Then the response code should be 404
    Then the result json should be:
    """
    {"errorCode":"BOOK_NOT_FOUND","errorMessage":"The book was not found.","params":{"isbn":"not-existing-isbn"}}
    """

  Scenario: find by author
    Given the web context is set
    Given the db is empty
    Given the following books exist:
    | isbn     |    title              |    author            |
    | isbn1234 | Hamlet                |  William Shakespeare |
    | isbn1235 | Romeo and Juliet      |  William Shakespeare |
    | isbn1236 | To Kill a Mockingbird |  Harper lee          |
    When client request GET /api/books?author=William%20Shakespeare
    Then the response code should be 200
    Then the result json should be:
    """
    [{"isbn":"isbn1234","title":"Hamlet","author":"William Shakespeare"},
     {"isbn":"isbn1235","title":"Romeo and Juliet","author":"William Shakespeare"}]
    """

  Scenario: find by title
    Given the web context is set
    Given the db is empty
    Given the following books exist:
    | isbn     |    title              |    author            |
    | isbn1234 | Hamlet                |  William Shakespeare |
    | isbn1235 | Romeo and Juliet      |  William Shakespeare |
    | isbn1236 | To Kill a Mockingbird |  Harper lee          |
    When client request GET /api/books?title=Romeo%20and%20Juliet
    Then the response code should be 200
    Then the result json should be:
    """
    [{"isbn":"isbn1235","title":"Romeo and Juliet","author":"William Shakespeare"}]
    """

  Scenario: find all
    Given the web context is set
    Given the db is empty
    Given the following books exist:
    | isbn     |    title              |    author            |
    | isbn1234 | Hamlet                |  William Shakespeare |
    | isbn1235 | Romeo and Juliet      |  William Shakespeare |
    | isbn1236 | To Kill a Mockingbird |  Harper lee          |
    When client request GET /api/books
    Then the response code should be 200
    Then the result json should be:
    """
    [{"isbn":"isbn1234", "title":"Hamlet","author":"William Shakespeare"},
     {"isbn":"isbn1235", "title":"Romeo and Juliet","author":"William Shakespeare"},
     {"isbn":"isbn1236", "title":"To Kill a Mockingbird","author":"Harper lee"}]
    """