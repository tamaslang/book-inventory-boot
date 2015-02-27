package org.talang.bookinventory.gateway;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.talang.bookinventory.api.BookDTO;
import org.talangsoft.rest.devtools.gateway.GatewayCommon;

@Service
public class IsbnGateway extends GatewayCommon {

    public static final String ISBN_GATEWAY_ENDPOINT = "http://some_3rd_party_org/api/register";

    public String registerBook(BookDTO book) {
        return genericPost(ISBN_GATEWAY_ENDPOINT, book, String.class);
    }

}
