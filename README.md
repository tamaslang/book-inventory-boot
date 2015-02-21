# Book inventory Spring boot app #

Spring Boot based implementation of a minimalist book inventory app to represent a possible module architecture.
This app offers basic CRUD functionality and some search functionality for handling books via book title and author.
The book entities are persisted in an underlying SQL database (in memory) for the sake of simplicity.
On the creation of a new Book it is registered via an imaginary 3rd party "register book" endpoint returning with an isbn number.

![Book Inventory app architecture](/docs/book_inventory_app.png "Inventory app architecture")

The book inventory app relies on the
<a href="http://www.talangsoft.org/2015/02/16/rest-devtools-introduction/" target="_blank">Rest Devtools</a> library.

Precondition: Install rest-devtools into your local repository.


### Create a book
POST http://localhost:8080/api/books
payload:
{ "author":"Tamas Lang","title":"Spring app architecture","isbn":null }
response:
201 Created with header: Location : http://localhost:8080/api/books/isbn1001

### Get a book by id (isbn)
GET http://localhost:8080/api/books/isbn1001
response:
{ "author":"Tamas Lang","title":"Spring app architecture","isbn":null }

### Get all books
GET http://localhost:8080/api/books
response:
[{ "author":"William Shakespeare","title":"Hamlet","isbn":null },
 { "author":"Paolo Giordani","title":"La solitudine dei numeri primi","isbn":null }]

### Update a book
PUT http://localhost:8080/api/books/isbn1001
payload:
{ "author":"Tamas Lang","title":"Spring app architecture","isbn":"isbn1001" }
response:
200 OK

### Get by author
GET http://localhost:8080/api/books?author=William
response:
[{ "author":"William Shakespeare","title":"Hamlet","isbn":null }]

### Get by title
GET http://localhost:8080/api/books?title=Hamlet
response:
200 OK
[{ "author":"William Shakespeare","title":"Hamlet","isbn":null }]

### Delete
DELETE http://localhost:8080/api/books?title=Hamlet
response:
200 OK