package org.talangsoft.bookinventory.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.mock.http.client.MockClientHttpRequest;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.client.MockRestServiceServer;
import org.talangsoft.bookinventory.gateway.IsbnGateway;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;


/* Only for mocking */
@Component
public class MockGatewayConfiguration implements InitializingBean {
    private MockRestServiceServer mockServer;

    @Autowired
    private IsbnGateway isbnGateway;

    private static final AtomicInteger bookId = new AtomicInteger(1000);

    @Override
    public void afterPropertiesSet() throws Exception {
        setUpMockServer();
    }

    private void setUpMockServer() throws Exception{
        isbnGateway.getRestTemplate().setRequestFactory(createMockRequestFactory());
    }

    private ClientHttpRequestFactory createMockRequestFactory() {
        return new ClientHttpRequestFactory() {
            @Override
            public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
                MockClientHttpRequest mockRequest = new MockClientHttpRequest();
                mockRequest.setResponse(new MockClientHttpResponse(generateBookId().getBytes(), HttpStatus.OK));
                return mockRequest;
            }
        };
    }

    private String generateBookId() {
        return "isbn"+bookId.getAndIncrement();
    }



}
