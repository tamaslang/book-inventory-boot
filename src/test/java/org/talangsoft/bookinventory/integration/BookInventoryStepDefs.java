package org.talangsoft.bookinventory.integration;


import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.talangsoft.bookinventory.Application;
import org.talangsoft.bookinventory.domain.Book;
import org.talangsoft.bookinventory.gateway.IsbnGateway;
import org.talangsoft.bookinventory.repository.BookRepository;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class BookInventoryStepDefs {
    @Autowired
    IsbnGateway isbnGateway;

    @Autowired
    BookRepository bookRepo;

    private MockRestServiceServer mockServer;

    @Before
    public void setUpMockServer(){
        mockServer = MockRestServiceServer.createServer(isbnGateway.getRestTemplate());
    }

    @Given("^the db is empty$")
    public void clearDb() throws Throwable {
        bookRepo.deleteAll();
    }


    @Given("^the following books exist:$")
    public void createBooks(DataTable books) throws Throwable {
        List<Book> bookList = books.asList(Book.class);
        bookRepo.save(bookList);
    }

    @Given("^the isbn gateway is mocked to success$")
    public void isbnGatewayIsMockedToSuccessResponse() {
        mockServer.expect(requestTo(IsbnGateway.ISBN_GATEWAY_ENDPOINT))
                .andRespond(withSuccess("isbn1234", MediaType.APPLICATION_JSON));
    }

    @Given("^the isbn gateway is mocked to error$")
    public void isbnGatewayIsMockedToErrorResponse() {
        mockServer.expect(requestTo(IsbnGateway.ISBN_GATEWAY_ENDPOINT))
                .andRespond(withServerError());
    }
}
