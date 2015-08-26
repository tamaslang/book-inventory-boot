package org.talangsoft.bookinventory.jms;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.talangsoft.bookinventory.Application;
import org.talangsoft.bookinventory.api.BookDTO;
import org.talangsoft.bookinventory.service.BookService;

import javax.jms.Destination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class BookMgrQueueListenerIntegrationTest {

    @Autowired(required = false)
    private JmsTemplate jmsTemplate;

    @Autowired
    private BookMgrQueueListener bookMgrQueueListener;


    @Autowired(required = false)
    @Qualifier("bookMgrQueueDestination")
    private Destination bookMgrQueueDestination;

    @Mock
    private BookService mockBookService;

    @Captor
    private ArgumentCaptor<BookDTO> bookArgumentCaptor;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(bookMgrQueueListener, "bookService", mockBookService);
    }

    @Test
    public void testContextSetup(){
        assertNotNull("JmsTemplate is null", jmsTemplate);
        assertNotNull("BookMgQueueDestination is null", bookMgrQueueDestination);
    }

    @Test
    public void testSendCreateBookMessage(){
        BookDTO book =  new BookDTO("isbn", "title", "author");
        jmsTemplate.convertAndSend(bookMgrQueueDestination, book, Message -> {
            return OperationHeader.CREATE.applyToMessage(Message);
        });
        // verify
        verify(mockBookService).createNew(bookArgumentCaptor.capture());
        assertEquals(book.getIsbn(), bookArgumentCaptor.getValue().getIsbn());
        assertEquals(book.getTitle(), bookArgumentCaptor.getValue().getTitle());
        assertEquals(book.getAuthor(), bookArgumentCaptor.getValue().getAuthor());
    }

    @Test
    public void testSendUpdateBookMessage(){
        BookDTO book =  new BookDTO("isbn", "title", "author");
        jmsTemplate.convertAndSend(bookMgrQueueDestination, book, Message -> {
            return OperationHeader.UPDATE.applyToMessage(Message);
        });
        // verify
        verify(mockBookService).update(eq(book.getIsbn()), bookArgumentCaptor.capture());
        assertEquals(book.getIsbn(), bookArgumentCaptor.getValue().getIsbn());
        assertEquals(book.getTitle(),bookArgumentCaptor.getValue().getTitle());
        assertEquals(book.getAuthor(),bookArgumentCaptor.getValue().getAuthor());
    }

    @Test
    public void testSendDeleteBookMessage(){
        BookDTO book =  new BookDTO("isbn", "title", "author");
        jmsTemplate.convertAndSend(bookMgrQueueDestination, book, Message -> {
            return OperationHeader.DELETE.applyToMessage(Message);
        });
        // verify
        verify(mockBookService).delete(book.getIsbn());
    }
}
