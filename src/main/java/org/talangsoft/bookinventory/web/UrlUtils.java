package org.talangsoft.bookinventory.web;

import org.talangsoft.bookinventory.api.RestErrors;
import org.talangsoft.rest.devtools.web.RestException;
import org.talangsoft.rest.devtools.web.util.PNV;
import org.talangsoft.rest.devtools.web.util.RestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlUtils {


    public static final String ENCODING_UTF_8 = "UTF-8";

    private UrlUtils() {
        // utility
    }

    public static  String decodeUrlParam(String param) {
        try {
            return URLDecoder.decode(param, ENCODING_UTF_8);
        } catch(UnsupportedEncodingException ex){
            throw new RestException(RestErrors.URL_ENCODING_NOT_SUPPORTED.toRestError(),
                    RestUtils.createParams(PNV.toPNV("text", param)));
        }
    }
}
