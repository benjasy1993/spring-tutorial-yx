package com.banking.young.clients;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

public class AccountServiceClientErrorHandler extends DefaultResponseErrorHandler {


    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        try {
            super.handleError(response);
        } catch (HttpClientErrorException e) {
            throw new RestClientException(e.getResponseBodyAsString());
        }
    }
}
